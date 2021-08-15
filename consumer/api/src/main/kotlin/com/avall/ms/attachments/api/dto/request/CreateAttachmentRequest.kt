package com.avall.ms.attachments.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

/*
   DTO for request model
 */
data class CreateAttachmentRequest /*@JsonCreator*/ (
        var description: String?,
        @NotBlank var contentType: String?,
        @NotBlank var url: String?,
        @set:JsonProperty("is_private")
        @get:JsonProperty("is_private")
        var isPrivate: Boolean?

) {
        constructor():this(
                description = null,
                contentType =  null,
                url = null,
                isPrivate = null
        ) {}
}
