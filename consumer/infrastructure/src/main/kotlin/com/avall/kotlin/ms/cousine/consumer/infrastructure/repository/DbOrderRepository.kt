package com.avall.kotlin.ms.cousine.consumer.infrastructure.repository


import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.OrderDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbOrderRepository : JpaRepository<OrderDb, String>