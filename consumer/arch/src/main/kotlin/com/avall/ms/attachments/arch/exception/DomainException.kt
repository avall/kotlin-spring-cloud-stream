package com.avall.ms.attachments.arch.exception

import com.avall.ms.attachments.arch.errors.ErrorReasonDTO

open class DomainException(
    val code: String,
    val responseCode:Int,
    message:String,
    val errorReasons: List<ErrorReasonDTO>? = null)
: Exception(message) {}

