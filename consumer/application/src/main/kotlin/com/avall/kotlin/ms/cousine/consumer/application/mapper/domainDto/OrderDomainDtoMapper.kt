package com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.OrderResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.Status
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.CustomerDomainDtoMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.CustomerDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.OrderItemDomainDtoMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.OrderItemDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.StoreDomainDtoMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.StoreDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import org.springframework.stereotype.Component

@Component
object OrderDomainDtoMapper {

    fun Order.mapToDto(): OrderResponse {
        return OrderResponse(
            id = this.id!!,
            date = this.createdAt,
            customer = this.customer.mapToDto(),
            store = this.store.mapToDto(),
            contact = this.customer.name,
            total = this.total,
            status = Status.valueOf(this.status.toString()),
            lastUpdate = this.updatedAt,
            orderItems = this.orderItems.mapToDto()
        )
    }

    fun List<Order>.mapToDto(): List<OrderResponse> {
        val orderListResponse = ArrayList<OrderResponse>()
        forEach { order -> orderListResponse.add(order.mapToDto()) }
        return orderListResponse
    }

    fun OrderResponse.mapToDomain(): Order {
        return Order(
            id = this.id,
            status = com.avall.kotlin.ms.cousine.consumer.domain.model.Status.valueOf(this.status.toString()),
            customer = this.customer.mapToDomain(),
            store = this.store.mapToDomain(),
            orderItems = this.orderItems.mapToDomain(),
            total = this.total,
            createdAt = this.date,
            updatedAt = this.lastUpdate
        )
    }

    fun List<OrderResponse>.mapToDomain(): List<Order> {
        val orderList = ArrayList<Order>()
        forEach { order -> orderList.add(order.mapToDomain()) }
        return orderList
    }

}