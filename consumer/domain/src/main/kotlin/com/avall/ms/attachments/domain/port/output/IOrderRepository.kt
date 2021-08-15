package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Order
import java.util.*

interface IOrderRepository {
    fun persist(order: Order): Order
    fun getById(id: Identity): Optional<Order>
}