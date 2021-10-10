package com.avall.kotlin.ms.cousine.consumer.arch.exception

//open class NotFoundException(code: String, message: String): DomainException(code, 404, message) {}

open class NotFoundException : DomainException {
    constructor(message: String) : super(message, "404", 400) {}
    constructor(messageFormat: String?, vararg args: Any?) : super(String.format(messageFormat!!, *args), "404", 400, null) {}
}