package com.avall.kotlin.ms.cousine.consumer.application.mapper.inputOutputDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.request.OrderRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.OrderRequestItem
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.UserPrincipal
import com.avall.kotlin.ms.cousine.consumer.domain.model.Customer
import com.avall.kotlin.ms.cousine.consumer.domain.model.OrderItem
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.order.CreateOrderUseCase
import org.springframework.stereotype.Component

@Component
object CreateOrderInputMapper {

    fun map(orderRequest: OrderRequest, userPrincipal: UserPrincipal): CreateOrderUseCase.InputValues {
        return CreateOrderUseCase.InputValues(
            customer = Customer(
                id = userPrincipal.id!!,
                name = userPrincipal.name!!,
                email = userPrincipal.email!!,
                address = userPrincipal.address!!,
                password = userPrincipal.password
            ),
            storeId = orderRequest.storeId, orderItems = orderRequest.orderItems.map())
    }

//    private fun OrderRequestItem.map(): CreateOrderUseCase.InputItem {
//        return CreateOrderUseCase.InputItem(id = String(this.id), quantity = this.quantity)
//    }
//
//    fun List<OrderRequestItem>.map(): List<CreateOrderUseCase.InputItem> {
//        val orderRequestItemList = ArrayList<CreateOrderUseCase.InputItem>()
//        forEach { orderRequestItem -> orderRequestItemList.add(orderRequestItem.map()) }
//        return orderRequestItemList
//    }

    private fun OrderRequestItem.map(): OrderItem {
        return OrderItem(id = this.id, quantity = this.quantity)
    }

    fun List<OrderRequestItem>.map(): List<OrderItem> {
        val orderRequestItemList = ArrayList<OrderItem>()
        forEach { orderRequestItem -> orderRequestItemList.add(orderRequestItem.map()) }
        return orderRequestItemList
    }

}