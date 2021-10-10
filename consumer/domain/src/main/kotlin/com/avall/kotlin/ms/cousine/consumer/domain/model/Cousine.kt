package com.avall.kotlin.ms.cousine.consumer.domain.model

data class Cousine(
    override var id: String?,
    var name: String
) : BaseDomainEntity()