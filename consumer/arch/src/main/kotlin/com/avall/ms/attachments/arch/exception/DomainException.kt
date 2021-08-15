package com.avall.ms.attachments.arch.exception

import com.avall.ms.attachments.arch.errors.ErrorReasonDTO

open class DomainException(
    message:String?,
    val code: String?,
    val responseCode:Int?,
    val errorReasons: List<ErrorReasonDTO>? = null)
: RuntimeException(message) {

    constructor(message: String?) : this(message, null, null, null) {}
    constructor(messageFormat: String?, vararg args: Any?) : this(String.format(messageFormat!!, *args), null, null, null) {}


}


