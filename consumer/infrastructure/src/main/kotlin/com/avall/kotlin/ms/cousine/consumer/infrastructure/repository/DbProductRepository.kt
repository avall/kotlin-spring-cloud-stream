package com.avall.kotlin.ms.cousine.consumer.infrastructure.repository

import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.ProductDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbProductRepository : JpaRepository<ProductDb, String> {
    fun findByNameContainingOrDescriptionContainingAllIgnoreCase(name: String, description: String): List<ProductDb?>
    fun findByStoreIdAndIdIsIn(id: String, ids: List<String>): List<ProductDb?>
}