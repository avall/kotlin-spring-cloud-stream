package com.avall.kotlin.ms.cousine.producer.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

/*
   DTO for request model
 */
data class CreateAttachmentResponse /*@JsonCreator*/ (
        var id: String,
        var objectId: String,
        var objectName: String,
        var description: String?,
        var contentType: String,
        var url: String,

        @get:JsonProperty("is_private")
        var isPrivate: Boolean?

)
