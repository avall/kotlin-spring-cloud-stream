package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.model.Store
import java.util.*

interface IStoreRepository {
    fun all(): List<Store>
    fun searchByName(searchText: String): List<Store>
    fun getById(id: String): Optional<Store>
    fun getProductsById(id: String): List<Product>
    fun getStoresById(id: String): List<Store>
}