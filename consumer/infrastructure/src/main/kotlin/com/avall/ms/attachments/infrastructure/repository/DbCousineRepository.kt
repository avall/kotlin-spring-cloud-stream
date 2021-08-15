package com.avall.ms.attachments.infrastructure.repository

import com.avall.ms.attachments.infrastructure.database.CousineDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbCousineRepository : JpaRepository<CousineDb, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<CousineDb?>
}