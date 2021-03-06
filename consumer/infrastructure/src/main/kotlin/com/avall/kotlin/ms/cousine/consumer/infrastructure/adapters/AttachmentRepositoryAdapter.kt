package com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException
import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IGetAttachmentPort
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IGetAttachmentsPort
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.ISaveAttachmentPort
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.AttachmentEntityMapper
import com.avall.kotlin.ms.cousine.consumer.infrastructure.repository.AttachmentRepository
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

    override fun findByObjectId(objectId:String): List<Attachment> =
        attachmentRepository.findByObjectId(objectId).map { AttachmentEntityMapper.toDomain(it) }
}