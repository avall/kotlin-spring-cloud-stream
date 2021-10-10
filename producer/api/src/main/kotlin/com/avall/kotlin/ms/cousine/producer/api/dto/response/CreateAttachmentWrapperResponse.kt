package com.avall.kotlin.ms.cousine.producer.api.dto.response

data class CreateAttachmentWrapperResponse constructor(
        var documents : List<CreateAttachmentResponse>
) {
        constructor():this(listOf()) {}
}

