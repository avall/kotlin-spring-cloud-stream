package com.avall.kotlin.ms.cousine.consumer.arch.exception

open class BadRequestException(code: String, message: String): DomainException(code, 400, message) {}