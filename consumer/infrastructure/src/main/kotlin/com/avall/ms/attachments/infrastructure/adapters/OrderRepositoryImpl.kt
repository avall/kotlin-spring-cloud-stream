package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Order
import com.avall.ms.attachments.domain.port.output.IOrderRepository
import com.avall.ms.attachments.infrastructure.database.OrderDb
import com.avall.ms.attachments.infrastructure.mapper.OrderDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.mapper.OrderDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.mapper.OrderItemDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.repository.DbOrderRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class OrderRepositoryImpl(
    private val repository: DbOrderRepository,
) : IOrderRepository {

    override fun persist(order: Order): Order {
        val orderDb: OrderDb = order.mapToDb()
        orderDb.orderItems = order.orderItems.mapToDb()
        orderDb.orderItems.forEach { orderItem -> orderItem.order = orderDb }
        return repository.save(orderDb).mapToDomain()
    }

    override fun getById(id: Identity): Optional<Order> {
        return repository
            .findById(id.number)
            .map { o -> o?.mapToDomain() }
    }
}
