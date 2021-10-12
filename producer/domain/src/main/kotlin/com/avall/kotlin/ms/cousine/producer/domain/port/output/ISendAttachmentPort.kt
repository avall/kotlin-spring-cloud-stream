package com.avall.kotlin.ms.cousine.producer.domain.port.output

import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment

interface ISendAttachmentPort {
    fun execute(attachments: List<Attachment>)
}