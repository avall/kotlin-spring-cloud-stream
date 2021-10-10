package com.avall.kotlin.ms.cousine.consumer.domain.model

data class Attachment(
        val id: String?=null,
        val objectId: String,
        val objectName: String,
        val description : String?,
        val contentType : String,
        val isPrivate : Boolean?=null,
        val url : String
)