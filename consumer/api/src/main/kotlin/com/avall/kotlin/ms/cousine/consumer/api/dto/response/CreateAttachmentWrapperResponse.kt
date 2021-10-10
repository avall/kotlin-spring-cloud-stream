package com.avall.kotlin.ms.cousine.consumer.api.dto.response

data class CreateAttachmentWrapperResponse constructor(
        var documents : List<CreateAttachmentResponse>
) {
        constructor():this(listOf()) {}
}

