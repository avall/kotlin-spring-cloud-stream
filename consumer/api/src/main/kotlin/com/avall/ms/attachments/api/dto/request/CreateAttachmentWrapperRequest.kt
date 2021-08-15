package com.avall.ms.attachments.api.dto.request

data class CreateAttachmentWrapperRequest constructor(
        var documents : List<CreateAttachmentRequest>
) {
        constructor():this(listOf()) {}
}

