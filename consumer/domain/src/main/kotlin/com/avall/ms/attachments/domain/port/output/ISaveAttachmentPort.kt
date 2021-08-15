package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Attachment

interface ISaveAttachmentPort {
    fun store(attachments: List<Attachment>): List<Attachment>
}