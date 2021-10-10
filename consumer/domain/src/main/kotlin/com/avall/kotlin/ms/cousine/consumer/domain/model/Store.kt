package com.avall.kotlin.ms.cousine.consumer.domain.model

data class Store(
    override var id: String?,
    var name: String,
    var address: String,
    var cousine: Cousine,
) : BaseDomainEntity()