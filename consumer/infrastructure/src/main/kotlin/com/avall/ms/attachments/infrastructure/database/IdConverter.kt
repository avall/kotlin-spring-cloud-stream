package com.avall.ms.attachments.infrastructure.database

import com.avall.ms.attachments.domain.model.Identity


object IdConverter {
    fun convertId(id: Identity?): Long? {
        return id?.number
    }
}