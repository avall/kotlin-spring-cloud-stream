package com.avall.ms.attachments.api.dto.request

data class OrderRequest (
    var storeId: Long,
    var orderItems: List<OrderRequestItem>
)