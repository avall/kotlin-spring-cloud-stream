package com.avall.ms.attachments.domain.model

data class OrderItem(
    override var id: String?,
    var quantity: Int,
    var product: Product?,
    var price: Double?,
    var total: Double?
) : BaseDomainEntity() {
    constructor(id:String, quantity: Int): this(id, quantity, null, null, null) {}
    constructor(quantity: Int, product: Product?, price: Double?, total: Double?): this(null, quantity, product, price, total) {}

}