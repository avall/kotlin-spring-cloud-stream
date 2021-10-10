package com.avall.kotlin.ms.cousine.consumer.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAttachmentResponse (
        val id: String,
        val objectId: String,
        val objectName: String,
        val contentType : String,
        val url : String,
        val description: String?,
        @get:JsonProperty("is_private")
        val isPrivate: Boolean?
)
