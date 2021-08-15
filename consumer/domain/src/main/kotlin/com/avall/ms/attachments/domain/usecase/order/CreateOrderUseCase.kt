package com.avall.ms.attachments.domain.usecase.order

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.*
import com.avall.ms.attachments.domain.port.output.IOrderRepository
import com.ferraobox.qamyapp.application.core.usecases.product.GetProductsByStoreAndProductsIdUseCase
import java.time.Instant

@Interactor
open class CreateOrderUseCase(
    private val getProductsByStoreAndProductsIdUseCase: GetProductsByStoreAndProductsIdUseCase,
    private val orderRepository: IOrderRepository
) : UseCase<CreateOrderUseCase.InputValues, CreateOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(orderRepository.persist(createOrder(input)))
    }

    private fun createOrder(input: InputValues): Order {
        val orderItems: List<OrderItem> = createOrderItems(input)
        return Order(
            id = Identity(),
            customer = input.customer,
            store = getFirstProductStore(orderItems),
            orderItems = orderItems,
            status = Status.OPEN,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            total = Order.calculateTotal(orderItems)
        )
    }

    private fun getFirstProductStore(orderItems: List<OrderItem>): Store {
        return orderItems[0].product.store
    }

    private fun createOrderItems(input: InputValues): List<OrderItem> {
        val productMap: Map<Identity, Product> = getProducts(input)
        return input.orderItems.map { inputItem -> createOrderItem(inputItem, productMap) }.toList()
    }

    private fun createOrderItem(inputItem: OrderItem, productMap: Map<Identity, Product>): OrderItem {
        val productId = inputItem.id
        val product: Product = productMap[productId]!!
        return OrderItem(
            id = Identity(),
            product = product,
            quantity = inputItem.quantity,
            total = (inputItem.quantity * product.price),
            price = product.price
        )
    }

    private fun getProducts(input: InputValues): Map<Identity, Product> {
        val inputValues: GetProductsByStoreAndProductsIdUseCase.InputValues =
            GetProductsByStoreAndProductsIdUseCase.InputValues(
                storeId = input.storeId,
                productsId = createListOfProductsId(input.orderItems)
            )
        return getProductsByStoreAndProductsIdUseCase.execute(inputValues).products.associateBy { it.id }
    }

    private fun createListOfProductsId(inputItems: List<OrderItem>): List<Identity> {
        return inputItems.map { it.id }.toList<Identity>()
    }

    data class InputValues(
        var customer: Customer,
        var storeId: Identity,
        var orderItems: List<OrderItem>
    ) : UseCase.InputValues

    data class OutputValues(
        val order: Order?
    ) : UseCase.OutputValues

    data class InputItem(
        var id: Identity,
        var quantity: Int = 0
    )
}