package com.avall.kotlin.ms.cousine.consumer.domain.model

data class Customer(
    override var id: String?,
    var name: String,
    var email: String,
    var address: String,
    var password: String,
) : BaseDomainEntity() {
    constructor(name: String, email: String, address: String, password: String):this(null, name, email, address, password)
}