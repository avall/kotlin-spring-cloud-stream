package com.avall.kotlin.ms.cousine.consumer.infrastructure.repository

import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.CustomerDb
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DbCustomerRepository : JpaRepository<CustomerDb, String> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<CustomerDb?>
}