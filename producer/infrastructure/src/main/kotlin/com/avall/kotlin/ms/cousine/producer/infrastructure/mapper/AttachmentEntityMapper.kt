package com.avall.kotlin.ms.cousine.producer.infrastructure.mapper

import com.avall.kotlin.ms.cousine.producer.CommandAttachment
import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import java.util.stream.Collectors

class AttachmentEntityMapper() {
    companion object {
        fun toEvent(attachment: Attachment): CommandAttachment {
            return CommandAttachment(attachment)
        }

        fun toPayload(attachments: List<Attachment>): CommandPayload {
            return CommandPayload(
                documents = attachments.stream()
                    .map { CommandAttachment(it) }
                    .collect(Collectors.toList())
            )
        }

        private fun CommandAttachment(attachment: Attachment):CommandAttachment =
            CommandAttachment(
                objectId = attachment.objectId,
                objectName = attachment.objectName,
                contentType = attachment.contentType,
                description = attachment.description,
                isPrivate   = attachment.isPrivate,
                url = attachment.url
        )

    }
}