package com.avall.kotlin.ms.cousine.consumer.infrastructure.repository

import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.AttachmentDb
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AttachmentRepository : JpaRepository<AttachmentDb, String> {
    fun findByObjectId(objectId: String): List<AttachmentDb>
}