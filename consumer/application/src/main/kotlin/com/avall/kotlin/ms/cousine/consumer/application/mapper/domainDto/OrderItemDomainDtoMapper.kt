package com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.OrderItemResponse
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.ProductDomainDtoMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.ProductDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.domain.model.OrderItem
import org.springframework.stereotype.Component

@Component
object OrderItemDomainDtoMapper {

    fun OrderItem.mapToDto(): OrderItemResponse {
        return OrderItemResponse(
            product = this.product!!.mapToDto(), price = this.price ?: 0.0, quantity = this.quantity, total = this.total ?: 0.0
        )
    }

    fun List<OrderItem>.mapToDto(): List<OrderItemResponse> {
        val orderItemListResponse = ArrayList<OrderItemResponse>()
        forEach { orderItem -> orderItemListResponse.add(orderItem.mapToDto()) }
        return orderItemListResponse
    }

    fun OrderItemResponse.mapToDomain(): OrderItem {
        return OrderItem(
            id = null,
            quantity = this.quantity,
            product = this.product.mapToDomain(),
            price = this.price,
            total = this.total
        )
    }

    fun List<OrderItemResponse>.mapToDomain(): List<OrderItem> {
        val orderItemList = ArrayList<OrderItem>()
        forEach { orderItem -> orderItemList.add(orderItem.mapToDomain()) }
        return orderItemList
    }
}
