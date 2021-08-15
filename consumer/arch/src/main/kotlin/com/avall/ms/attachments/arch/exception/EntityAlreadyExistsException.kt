package com.avall.ms.attachments.arch.exception

import com.avall.ms.attachments.arch.exception.DomainException

open class EntityAlreadyExistsException(message: String?) : DomainException("Entity already exists",406 , message!!, listOf())