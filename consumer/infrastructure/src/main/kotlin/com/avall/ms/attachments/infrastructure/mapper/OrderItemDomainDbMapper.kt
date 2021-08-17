package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.OrderItem
import com.avall.ms.attachments.infrastructure.database.OrderItemDb
import com.avall.ms.attachments.infrastructure.mapper.ProductDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.mapper.ProductDomainDbMapper.mapToDomain


object OrderItemDomainDbMapper {

    fun OrderItem.mapToDb(): OrderItemDb {
        return OrderItemDb(
            id = this.id,
            order = null,
            quantity = this.quantity,
            product = this.product!!.mapToDb(),
            total = this.total ?: 0.0,
            price = this.price ?: 0.0
        )
    }

    fun List<OrderItem>.mapToDb(): MutableSet<OrderItemDb> {
        val orderItemDbList = HashSet<OrderItemDb>()
        forEach { orderItem -> orderItemDbList.add(orderItem.mapToDb()) }
        return orderItemDbList
    }


    fun OrderItemDb.mapToDomain(): OrderItem {
        return OrderItem(
            id = this.id,
            quantity = this.quantity,
            product = this.product.mapToDomain(),
            total = this.total,
            price = this.price
        )
    }

    fun MutableSet<OrderItemDb>.mapToDomain(): List<OrderItem> {
        val orderItemList = ArrayList<OrderItem>()
        forEach { orderItemDb -> orderItemList.add(orderItemDb.mapToDomain()) }
        return orderItemList
    }

}