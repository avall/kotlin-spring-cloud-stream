package com.avall.ms.attachments.infrastructure.repository


import com.avall.ms.attachments.infrastructure.database.OrderDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbOrderRepository : JpaRepository<OrderDb, String>