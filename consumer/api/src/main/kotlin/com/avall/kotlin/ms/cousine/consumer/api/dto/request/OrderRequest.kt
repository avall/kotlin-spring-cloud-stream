package com.avall.kotlin.ms.cousine.consumer.api.dto.request

data class OrderRequest (
    var storeId: String,
    var orderItems: List<OrderRequestItem>
)