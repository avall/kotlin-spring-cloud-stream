package com.avall.ms.attachments

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class CommandPayload @JsonCreator constructor(
    @JsonProperty("documents") val documents: List<CommandAttachment>
):Serializable