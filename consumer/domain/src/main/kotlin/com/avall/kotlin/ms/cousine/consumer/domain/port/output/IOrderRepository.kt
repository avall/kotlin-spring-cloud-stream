package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import java.util.*

interface IOrderRepository {
    fun persist(order: Order): Order
    fun getById(id: String): Optional<Order>
}