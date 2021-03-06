package com.avall.kotlin.ms.cousine.consumer.api.dto.response

data class ProductResponse (
    var id: String,
    var name: String,
    var description: String,
    var price: Double,
    var store: StoreResponse,
)