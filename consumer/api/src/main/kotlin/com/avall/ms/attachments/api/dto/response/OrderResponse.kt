package com.avall.ms.attachments.api.dto.response

import java.time.Instant

data class OrderResponse (
    var id: String,
    var date: Instant,
    var customer: CustomerResponse,
    var store: StoreResponse,
    var contact: String,
    var total: Double,
    var status: Status,
    var lastUpdate: Instant,
    var orderItems: List<OrderItemResponse>
)