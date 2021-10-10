package com.avall.kotlin.ms.cousine.consumer.application.exceptions

import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException

class AttachmentNotFoundException(id: String) : NotFoundException(id, "Attachment") {
}