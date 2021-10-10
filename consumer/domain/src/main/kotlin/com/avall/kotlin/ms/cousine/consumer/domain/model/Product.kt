package com.avall.kotlin.ms.cousine.consumer.domain.model

data class Product(
    override var id: String?,
    var name: String,
    var description: String,
    var price: Double,
    var store: Store,
) : BaseDomainEntity()