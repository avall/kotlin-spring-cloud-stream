package com.avall.kotlin.ms.cousine.consumer.infrastructure.database

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

open class BaseDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: String? = null
}