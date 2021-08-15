package com.avall.ms.attachments.infrastructure.database

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

open class BaseDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null
}