package com.avall.ms.attachments.domain.usecase.order

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.*
import com.avall.ms.attachments.domain.port.output.IOrderRepository
import com.avall.ms.attachments.domain.usecase.product.GetProductsByStoreAndProductsIdUseCase
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
        return orderItems[0].product!!.store
    }

    private fun createOrderItems(input: InputValues): List<OrderItem> {
        val productMap: Map<String, Product> = getProducts(input)
        return input.orderItems.map { inputItem -> createOrderItem(inputItem, productMap) }.toList()
    }

    private fun createOrderItem(inputItem: OrderItem, productMap: Map<String, Product>): OrderItem {
        val productId = inputItem.id
        val product: Product = productMap[productId]!!
        return OrderItem(
            product = product,
            quantity = inputItem.quantity,
            total = (inputItem.quantity * product.price),
            price = product.price
        )
    }

    private fun getProducts(input: InputValues): Map<String, Product> {
        val inputValues: GetProductsByStoreAndProductsIdUseCase.InputValues =
            GetProductsByStoreAndProductsIdUseCase.InputValues(
                storeId = input.storeId,
                productsId = createListOfProductsId(input.orderItems)
            )
        return getProductsByStoreAndProductsIdUseCase.execute(inputValues).products.associateBy { it.id!! }
    }

    private fun createListOfProductsId(inputItems: List<OrderItem>): List<String> {
        return inputItems.map { it.id!! }.toList<String>()
    }

    data class InputValues(
        var customer: Customer,
        var storeId: String,
        var orderItems: List<OrderItem>
    ) : UseCase.InputValues

    data class OutputValues(
        val order: Order?
    ) : UseCase.OutputValues

    data class InputItem(
        var id: String,
        var quantity: Int = 0
    )
}