package com.avall.ms.attachments.arch.exception

open class NotFoundException(code: String, message: String): DomainException(code, 404, message) {}