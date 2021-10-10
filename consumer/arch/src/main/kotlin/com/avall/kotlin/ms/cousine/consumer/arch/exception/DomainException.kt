package com.avall.kotlin.ms.cousine.consumer.arch.exception

import com.avall.kotlin.ms.cousine.consumer.arch.errors.ErrorReasonDTO

open class DomainException(
    override val message:String,
    val code: String,
    val responseCode:Int?,
    val errorReasons: List<ErrorReasonDTO>? = null)
: RuntimeException(message) {

    constructor(message: String) : this(
        message = message,
        code = "400",
        responseCode = 400,
        errorReasons = null) {}
    constructor(message: String, code: String, responseCode: Int) : this(
        message = message,
        code = code,
        responseCode = responseCode,
        errorReasons = null) {}
    constructor(messageFormat: String, vararg args: Any) :
            this(
                message = String.format(messageFormat!!, *args),
                code = "400",
                responseCode = 400,
                errorReasons = null) {}
    constructor(message: String, code: Int) : this(
        message = message,
        code = code.toString(),
        responseCode = code,
        errorReasons = null) {}
    constructor(messageFormat: String, vararg args: Any, code: Int) :
            this(
                message = String.format(messageFormat!!, *args),
                code = code.toString(),
                responseCode = code,
                errorReasons = null) {}
}


