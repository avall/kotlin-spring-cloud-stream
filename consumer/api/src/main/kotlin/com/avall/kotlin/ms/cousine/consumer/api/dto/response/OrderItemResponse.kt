package com.avall.kotlin.ms.cousine.consumer.api.dto.response


data class OrderItemResponse (
    var product: ProductResponse,
    var price: Double,
    var quantity: Int,
    var total: Double
)