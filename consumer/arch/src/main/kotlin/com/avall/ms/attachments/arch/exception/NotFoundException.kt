package com.avall.ms.attachments.arch.exception

//open class NotFoundException(code: String, message: String): DomainException(code, 404, message) {}

open class NotFoundException : DomainException {
    constructor(message: String?) : super(message, null, null, null) {}
    constructor(messageFormat: String?, vararg args: Any?) : super(String.format(messageFormat!!, *args), null, null, null) {}
}