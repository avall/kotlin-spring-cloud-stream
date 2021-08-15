package com.avall.ms.attachments.api.dto.response


data class OrderItemResponse (
    var product: ProductResponse,
    var price: Double,
    var quantity: Int,
    var total: Double
)