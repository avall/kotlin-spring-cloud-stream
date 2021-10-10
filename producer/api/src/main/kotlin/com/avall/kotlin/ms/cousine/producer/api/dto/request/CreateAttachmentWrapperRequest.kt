package com.avall.kotlin.ms.cousine.producer.api.dto.request

data class CreateAttachmentWrapperRequest constructor(
        var documents : List<CreateAttachmentRequest>
) {
        constructor():this(listOf()) {}
}

