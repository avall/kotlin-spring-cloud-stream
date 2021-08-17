package com.avall.ms.attachments.infrastructure.database

import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.infrastructure.mapper.AttachmentEntityMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExtendWith(MockitoExtension::class)
internal class AttachmentDbMapperTest {

    @Test
    fun `Give correct fields When AttachmentEntity is converted to Attachment`() {
        //Give
        val attachmentEntity = getAttachmentEntity()

        //When
        val attachment = AttachmentEntityMapper.toDomain(attachmentEntity)

        // then
        expectThat(attachment) {
            get { id }          isEqualTo attachmentEntity.id
            get { parentId }    isEqualTo attachmentEntity.parentId
            get { contentType } isEqualTo attachmentEntity.contentType
            get { url }         isEqualTo attachmentEntity.url
            get { description } isEqualTo attachmentEntity.description
            get { isPrivate }   isEqualTo attachmentEntity.isPrivate
        }
    }

    @Test
    fun `Give correct fields When list of AttachmentEntity is converted to list of Attachment`() {
        //Give
        val attachmentsEntity = listOf(getAttachmentEntity())

        //When
        val attachments = AttachmentEntityMapper.toDomain(attachmentsEntity)

        // then
        expectThat(attachments.get(0)) {
            get { id }          isEqualTo attachmentsEntity.get(0).id
            get { parentId }    isEqualTo attachmentsEntity.get(0).parentId
            get { contentType } isEqualTo attachmentsEntity.get(0).contentType
            get { url }         isEqualTo attachmentsEntity.get(0).url
            get { description } isEqualTo attachmentsEntity.get(0).description
            get { isPrivate }   isEqualTo attachmentsEntity.get(0).isPrivate
        }
    }

    @Test
    fun `Give correct fields When Attachment is converted to AttachmentEntity`() {
        //Give
        val attachment = getAttachment()

        //When
        val attachmentEntity = AttachmentEntityMapper.toEntity(attachment)

        // then
        expectThat(attachmentEntity) {
            get { id }          isEqualTo attachmentEntity.id
            get { parentId }    isEqualTo attachmentEntity.parentId
            get { contentType } isEqualTo attachmentEntity.contentType
            get { url }         isEqualTo attachmentEntity.url
            get { description } isEqualTo attachmentEntity.description
            get { isPrivate }   isEqualTo attachmentEntity.isPrivate
        }
    }

    @Test
    fun `Give correct fields When list of Attachment is converted to list of AttachmentEntity`() {
        //Give
        val attachments = listOf(getAttachment())

        //When
        val attachmentsEntity = AttachmentEntityMapper.toEntity(attachments)

        // then
        expectThat(attachmentsEntity.get(0)) {
            get { id }          isEqualTo attachmentsEntity.get(0).id
            get { parentId }    isEqualTo attachmentsEntity.get(0).parentId
            get { contentType } isEqualTo attachmentsEntity.get(0).contentType
            get { url }         isEqualTo attachmentsEntity.get(0).url
            get { description } isEqualTo attachmentsEntity.get(0).description
            get { isPrivate }   isEqualTo attachmentsEntity.get(0).isPrivate
        }
    }

    /**
     * Factory to create an attachment
     * @return Attachment
     */
    private fun getAttachment(): Attachment {
        return Attachment(
            id = "UUID",
            parentId = "parentId",
            contentType = "contentType",
            url = "url",
            description = "description",
            isPrivate = true
        )
    }

    /**
     * Factory to create an attachment
     * @return Attachment
     */
    private fun getAttachmentEntity(): AttachmentDb {
        return AttachmentDb(
            id = "UUID",
            parentId = "parentId",
            contentType = "contentType",
            url = "url",
            description = "description",
            isPrivate = true
        )
    }

}