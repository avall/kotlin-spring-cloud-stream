package com.avall.kotlin.ms.cousine.consumer

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/*
     Attachment message
 */
data class CommandAttachment @JsonCreator constructor(
    @JsonProperty("objectId") val objectId: String,
    @JsonProperty("objectName") val objectName: String,
    @JsonProperty("description") val description: String?=null,
    @JsonProperty("contentType") val contentType: String,
    @JsonProperty("isPrivate") val isPrivate: Boolean?=null,
    @JsonProperty("url") val url: String
)