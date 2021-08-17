package com.avall.ms.attachments.domain.model

data class OrderItem(
    override var id: Identity,
    var quantity: Int,
    var product: Product?,
    var price: Double?,
    var total: Double?
) : BaseDomainEntity() {
    constructor(id:Identity, quantity: Int):
        this(id, quantity, null, null, null) {}

}