package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Attachment

interface IGetAttachmentPort {
    fun get(id: String): Attachment
}