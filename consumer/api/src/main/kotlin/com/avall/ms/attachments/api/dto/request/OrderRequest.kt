package com.avall.ms.attachments.api.dto.request

data class OrderRequest (
    var storeId: String,
    var orderItems: List<OrderRequestItem>
)