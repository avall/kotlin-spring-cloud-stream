package com.avall.ms.attachments.domain.model

data class Attachment(
        val id: String?=null,
        val parentId: String,
        val description : String?,
        val contentType : String,
        val isPrivate : Boolean?=null,
        val url : String
)