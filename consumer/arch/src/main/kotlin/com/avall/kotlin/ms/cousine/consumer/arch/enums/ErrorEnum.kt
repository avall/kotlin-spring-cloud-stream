package com.avall.kotlin.ms.cousine.consumer.arch.enums

enum class ErrorEnum {
    INTERNAL_SERVER_ERROR{
        override fun getCode() = "ATTACHMENT-CRM-500"
        override fun getMessage() = "Internal Server Error"
     },
    INVALID_REQUEST_PARAMETERS {
        override fun getCode() = "ATTACHMENT-CRM-400-1"
        override fun getMessage() = "Invalid Required Input Parameters"

    },
    MISSING_HEADER {
        override fun getCode() = "ATTACHMENT-CRM-400-2"
        override fun getMessage() = "Missing Required Header %s"

    },
    FORBIDDEN() {
        override fun getCode() = "ATTACHMENT-CRM-403"
        override fun getMessage() = "Forbidden Access"
    };

    abstract fun getCode(): String
    abstract fun getMessage(): String
}