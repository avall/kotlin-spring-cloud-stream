package com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper

import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import com.avall.kotlin.ms.cousine.consumer.domain.model.Status
import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.OrderDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.StatusDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.CustomerDomainDbMapper.mapToDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.CustomerDomainDbMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.OrderItemDomainDbMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.StoreDomainDbMapper.mapToDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.StoreDomainDbMapper.mapToDomain


object OrderDomainDbMapper {

    fun Order.mapToDb(): OrderDb {
        return OrderDb(
            id = this.id,
            customer = this.customer.mapToDb(),
            store = this.store.mapToDb(),
            total = this.total,
            status = StatusDb.valueOf(this.status.toString()),
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    fun List<Order>.mapToDb(): List<OrderDb> {
        val orderDbList = ArrayList<OrderDb>()
        forEach { order ->
            orderDbList.add(order.mapToDb())
        }
        return orderDbList
    }

    fun OrderDb.mapToDomain(): Order {
        return Order(
            id = this.id,
            customer = this.customer.mapToDomain(),
            store = this.store.mapToDomain(),
            total = this.total,
            status = Status.valueOf(this.status.toString()),
            orderItems= this.orderItems.mapToDomain(),
            createdAt = this.updatedAt,
            updatedAt = this.updatedAt
        )
    }

    fun List<OrderDb>.mapToDomain(): List<Order> {
        val orderList = ArrayList<Order>()
        forEach { orderDb ->
            orderList.add(orderDb.mapToDomain())
        }
        return orderList
    }


}
