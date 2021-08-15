package com.avall.ms.attachments.domain.model

data class Cousine(
    override var id: Identity,
    var name: String
) : BaseDomainEntity()