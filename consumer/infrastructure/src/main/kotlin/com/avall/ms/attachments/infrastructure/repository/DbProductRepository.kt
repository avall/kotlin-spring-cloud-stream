package com.avall.ms.attachments.infrastructure.repository

import com.avall.ms.attachments.infrastructure.database.ProductDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbProductRepository : JpaRepository<ProductDb, String> {
    fun findByNameContainingOrDescriptionContainingAllIgnoreCase(name: String, description: String): List<ProductDb?>
    fun findByStoreIdAndIdIsIn(id: String, ids: List<String>): List<ProductDb?>
}