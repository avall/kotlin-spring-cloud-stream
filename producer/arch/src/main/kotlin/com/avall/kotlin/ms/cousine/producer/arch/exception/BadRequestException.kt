package com.avall.kotlin.ms.cousine.producer.arch.exception

open class BadRequestException(code: String, message: String): DomainException(code, 400, message) {}