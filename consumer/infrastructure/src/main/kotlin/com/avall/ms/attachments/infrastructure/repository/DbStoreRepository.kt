package com.avall.ms.attachments.infrastructure.repository

import com.avall.ms.attachments.infrastructure.database.ProductDb
import com.avall.ms.attachments.infrastructure.database.StoreDb
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DbStoreRepository : JpaRepository<StoreDb, String> {
    fun findByNameContainingIgnoreCase(name: String?): List<StoreDb?>

    @Query("select p from store s join s.products p where s.id = ?1")
    fun findProductsById(id: String): List<ProductDb?>

    @Query("select s from cousine c join c.stores s where c.id = ?1")
    fun findStoresById(id: String): List<StoreDb?>
}