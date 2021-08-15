package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Product
import java.util.*

interface IProductRepository {
    fun all(): List<Product>
    fun getById(id: Identity): Optional<Product>
    fun searchByNameOrDescription(searchText: String): List<Product>
    fun searchProductsByStoreAndProductsId(storeId: Identity, productsId: List<Identity>): List<Product>
}