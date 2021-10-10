package com.avall.kotlin.ms.cousine.producer.arch.exception

import com.avall.kotlin.ms.cousine.producer.arch.exception.DomainException

open class EntityAlreadyExistsException(message: String?) : DomainException("Entity already exists",406 , message!!)