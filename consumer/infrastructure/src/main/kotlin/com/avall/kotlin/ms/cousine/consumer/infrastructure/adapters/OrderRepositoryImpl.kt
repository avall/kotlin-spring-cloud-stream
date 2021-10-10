package com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IOrderRepository
import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.OrderDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.OrderDomainDbMapper.mapToDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.OrderDomainDbMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.OrderItemDomainDbMapper.mapToDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.repository.DbOrderRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class OrderRepositoryImpl(
    private val repository: DbOrderRepository,
) : IOrderRepository {

    override fun persist(order: Order): Order {
        val orderDb: OrderDb = order.mapToDb()
        orderDb.orderItems = order.orderItems.mapToDb()
        orderDb.orderItems.forEach { orderItem -> orderItem.order = orderDb }
        return repository.save(orderDb).mapToDomain()
    }

    override fun getById(id: String): Optional<Order> {
        return repository
            .findById(id)
            .map { o -> o?.mapToDomain() }
    }
}
