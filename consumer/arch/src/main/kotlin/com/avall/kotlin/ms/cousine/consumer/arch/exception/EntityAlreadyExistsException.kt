package com.avall.kotlin.ms.cousine.consumer.arch.exception

import com.avall.kotlin.ms.cousine.consumer.arch.exception.DomainException

open class EntityAlreadyExistsException(message: String?) : DomainException("Entity already exists",406 , message!!)