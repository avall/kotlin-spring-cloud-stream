package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.output.IGetAttachmentPort
import com.avall.ms.attachments.domain.port.output.IGetAttachmentsPort
import com.avall.ms.attachments.domain.port.output.ISaveAttachmentPort
import com.avall.ms.attachments.infrastructure.mapper.AttachmentEntityMapper
import com.avall.ms.attachments.infrastructure.repository.AttachmentRepository
import org.springframework.stereotype.Component

@Component
class AttachmentRepositoryAdapter(
    private val attachmentRepository: AttachmentRepository
) :
    ISaveAttachmentPort, IGetAttachmentPort, IGetAttachmentsPort {

    override fun store(attachments: List<Attachment>): List<Attachment> =
        AttachmentEntityMapper.toDomain(
            attachmentRepository.saveAll(
                AttachmentEntityMapper.toEntity(
                    attachments
                )
            )
        )

    override fun get(id: String): Attachment =
        attachmentRepository.findById(id).map { a -> AttachmentEntityMapper.toDomain(a) }.orElseThrow{ NotFoundException("404","No 'attachment' found with id $id") }

    override fun findByParentId(parentId:String): List<Attachment> =
        attachmentRepository.findByParentId(parentId).map { AttachmentEntityMapper.toDomain(it) }
}