package com.avall.kotlin.ms.cousine.producer.api.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

/*
   DTO for request model
 */
data class CreateAttachmentRequest /*@JsonCreator*/ (
        var description: String?,
        @NotBlank var objectId : String?,
        @NotBlank var objectName : String?,
        @NotBlank var contentType: String?,
        @NotBlank var url: String?,
        @set:JsonProperty("is_private")
        @get:JsonProperty("is_private")
        var isPrivate: Boolean?

) {
        constructor():this(
                objectId = null,
                objectName = null,
                description = null,
                contentType =  null,
                url = null,
                isPrivate = null
        ) {}
}
