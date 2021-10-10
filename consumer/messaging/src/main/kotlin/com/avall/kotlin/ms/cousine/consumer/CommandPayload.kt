package com.avall.kotlin.ms.cousine.consumer

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class CommandPayload @JsonCreator constructor(
    @JsonProperty("documents") val documents: List<CommandAttachment>
):Serializable