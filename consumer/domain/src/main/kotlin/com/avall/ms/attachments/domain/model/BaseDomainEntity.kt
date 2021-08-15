package com.avall.ms.attachments.domain.model

open class BaseDomainEntity(
    open var id: Identity
) {
    constructor() : this(Identity())
}
