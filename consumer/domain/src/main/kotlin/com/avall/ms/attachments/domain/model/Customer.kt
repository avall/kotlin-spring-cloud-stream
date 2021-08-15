package com.avall.ms.attachments.domain.model

data class Customer(
    override var id: Identity,
    var name: String,
    var email: String,
    var address: String,
    var password: String,
) : BaseDomainEntity()