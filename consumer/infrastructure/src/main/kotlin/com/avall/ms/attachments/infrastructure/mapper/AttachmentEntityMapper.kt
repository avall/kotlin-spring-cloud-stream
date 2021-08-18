package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.infrastructure.database.AttachmentDb
import java.util.stream.Collectors

class AttachmentEntityMapper() {
    companion object {
        fun toDomain(attachmentDb: AttachmentDb): Attachment {
            return attachment(attachmentDb)
        }

        fun toDomain(attachments: List<AttachmentDb>): List<Attachment> {
            return attachments.stream()
                .map { attachment(it) }
                .collect(Collectors.toList())
        }

        fun toEntity(attachment: Attachment): AttachmentDb {
            return attachmentDb(attachment)
        }

        fun toEntity(attachments: List<Attachment>): List<AttachmentDb> {
            return attachments.stream()
                .map { attachmentDb(it) }
                .collect(Collectors.toList())
        }

        private fun attachmentDb(attachment: Attachment):AttachmentDb =
            AttachmentDb(
                id = attachment.id,
                objectId = attachment.objectId,
                contentType = attachment.contentType,
                description = attachment.description,
                isPrivate   = attachment.isPrivate,
                url = attachment.url
        )

        private fun attachment(attachmentDb: AttachmentDb) =
            Attachment(
                id               = attachmentDb.id,
                objectId         = attachmentDb.objectId,
                description      = attachmentDb.description,
                contentType      = attachmentDb.contentType,
                isPrivate        = attachmentDb.isPrivate,
                url              = attachmentDb.url
        )
    }
}