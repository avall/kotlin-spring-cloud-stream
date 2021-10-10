package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment

interface ISaveAttachmentPort {
    fun store(attachments: List<Attachment>): List<Attachment>
}