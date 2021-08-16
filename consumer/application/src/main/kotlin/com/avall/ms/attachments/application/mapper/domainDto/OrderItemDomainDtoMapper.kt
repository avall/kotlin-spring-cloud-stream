package com.avall.ms.attachments.application.mapper.domainDto

import com.avall.ms.attachments.api.dto.response.OrderItemResponse
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.OrderItem
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDto

import org.springframework.stereotype.Component

@Component
object OrderItemDomainDtoMapper {

    fun OrderItem.mapToDto(): OrderItemResponse {
        return OrderItemResponse(
            product = this.product.mapToDto(), price = this.price, quantity = this.quantity, total = this.total
        )
    }

    fun List<OrderItem>.mapToDto(): List<OrderItemResponse> {
        val orderItemListResponse = ArrayList<OrderItemResponse>()
        forEach { orderItem -> orderItemListResponse.add(orderItem.mapToDto()) }
        return orderItemListResponse
    }

    fun OrderItemResponse.mapToDomain(): OrderItem {
        return OrderItem(
            id = Identity(),
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
