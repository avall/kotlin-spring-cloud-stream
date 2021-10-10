package com.avall.kotlin.ms.cousine.consumer.infrastructure.repository

import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.CousineDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbCousineRepository : JpaRepository<CousineDb, String> {
    fun findByNameContainingIgnoreCase(name: String): List<CousineDb?>
}