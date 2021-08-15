package com.avall.ms.attachments.infrastructure.repository

import com.avall.ms.attachments.infrastructure.database.ProductDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbProductRepository : JpaRepository<ProductDb, Long> {
    fun findByNameContainingOrDescriptionContainingAllIgnoreCase(name: String, description: String): List<ProductDb?>
    fun findByStoreIdAndIdIsIn(id: Long, ids: List<Long>): List<ProductDb?>
}