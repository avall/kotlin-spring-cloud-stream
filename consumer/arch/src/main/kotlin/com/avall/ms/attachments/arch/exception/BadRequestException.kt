package com.avall.ms.attachments.arch.exception

open class BadRequestException(code: String, message: String): DomainException(code, 400, message) {}