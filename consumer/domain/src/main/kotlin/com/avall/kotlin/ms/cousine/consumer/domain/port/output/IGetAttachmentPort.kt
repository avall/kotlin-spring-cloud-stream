package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment

interface IGetAttachmentPort {
    fun get(id: String): Attachment
}