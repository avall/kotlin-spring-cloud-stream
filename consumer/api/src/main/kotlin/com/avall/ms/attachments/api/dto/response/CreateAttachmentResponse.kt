package com.avall.ms.attachments.api.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

/*
   DTO for request model
 */
data class CreateAttachmentResponse /*@JsonCreator*/ (
        var id: String,
        var parentId: String,
        var description: String?,
        var contentType: String,
        var url: String,

        @get:JsonProperty("is_private")
        var isPrivate: Boolean?

)
