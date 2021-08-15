package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.model.Store
import java.util.*

interface IStoreRepository {
    fun all(): List<Store>
    fun searchByName(searchText: String): List<Store>
    fun getById(id: Identity): Optional<Store>
    fun getProductsById(id: Identity): List<Product>
    fun getStoresById(id: Identity): List<Store>
}