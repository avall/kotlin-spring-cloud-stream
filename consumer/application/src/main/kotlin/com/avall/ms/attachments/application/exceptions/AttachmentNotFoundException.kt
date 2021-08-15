package com.avall.ms.attachments.application.exceptions

import com.avall.ms.attachments.arch.exception.NotFoundException

class AttachmentNotFoundException(id: String) : NotFoundException(id, "Attachment") {
}