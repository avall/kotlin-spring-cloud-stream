package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.Product
import com.avall.kotlin.ms.cousine.consumer.domain.model.Store
import java.util.*

interface IStoreRepository {
    fun all(): List<Store>
    fun searchByName(searchText: String): List<Store>
    fun getById(id: String): Optional<Store>
    fun getProductsById(id: String): List<Product>
    fun getStoresById(id: String): List<Store>
}