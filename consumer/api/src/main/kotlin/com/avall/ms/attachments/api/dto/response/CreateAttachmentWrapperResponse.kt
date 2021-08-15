package com.avall.ms.attachments.api.dto.response

data class CreateAttachmentWrapperResponse constructor(
        var documents : List<CreateAttachmentResponse>
) {
        constructor():this(listOf()) {}
}

