package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.infrastructure.mapper.AttachmentEntityMapper
import com.avall.ms.attachments.infrastructure.repository.AttachmentRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class attachmentRepositoryAdapterTests {

    @Mock lateinit var attachmentRepository: AttachmentRepository
    @InjectMocks lateinit var attachmentRepositoryAdapter: AttachmentRepositoryAdapter

    @Test
    fun `When "store" Then AttachmentEntityMapper_toEntity and AttachmentRepository_save called`() {
        // Given
        val attachment = buildAttachment()
        val attachmentEntity = AttachmentEntityMapper.toEntity(attachment)

        `when`(attachmentRepository.saveAll(anyList())).thenReturn(listOf(attachmentEntity))

        // When
        val stored:List<Attachment> = attachmentRepositoryAdapter.store(listOf(attachment))

        // Then
        verify(attachmentRepository, times(1)).saveAll(anyList())

        expectThat(stored.get(0)) {
            get { id } isEqualTo attachmentEntity.id
            get { contentType } isEqualTo attachmentEntity.contentType
            get { description } isEqualTo attachmentEntity.description
            get { url } isEqualTo attachmentEntity.url
            get { isPrivate } isEqualTo attachmentEntity.isPrivate
        }
    }

    @Test
    fun `When "findById" Then AttachmentEntityMapper_toEntity and AttachmentRepository_save called`() {
        // Given
        val attachment = buildAttachment()
        val attachmentEntity = AttachmentEntityMapper.toEntity(attachment)

        `when`(attachmentRepository.findById(any())).thenReturn(Optional.ofNullable(attachmentEntity))

        // When
        val stored:Attachment = attachmentRepositoryAdapter.get("")

        // Then
        verify(attachmentRepository, times(1)).findById(any())

        expectThat(stored) {
            get { id } isEqualTo attachmentEntity.id
            get { contentType } isEqualTo attachmentEntity.contentType
            get { description } isEqualTo attachmentEntity.description
            get { url } isEqualTo attachmentEntity.url
            get { isPrivate } isEqualTo attachmentEntity.isPrivate
        }
    }


    @Test
    fun `When "findByParentId" Then AttachmentEntityMapper_toEntity and AttachmentRepository_save called`() {
        // Given
        val attachment = buildAttachment()
        val attachmentEntity = AttachmentEntityMapper.toEntity(attachment)

        `when`(attachmentRepository.findByParentId(any())).thenReturn(listOf(attachmentEntity))

        // When
        val stored:List<Attachment> = attachmentRepositoryAdapter.findByParentId("")

        // Then
        verify(attachmentRepository, times(1)).findByParentId(any())

        expectThat(stored.get(0)) {
            get { id } isEqualTo attachmentEntity.id
            get { objectId } isEqualTo attachmentEntity.objectId
            get { contentType } isEqualTo attachmentEntity.contentType
            get { description } isEqualTo attachmentEntity.description
            get { url } isEqualTo attachmentEntity.url
            get { isPrivate } isEqualTo attachmentEntity.isPrivate
        }
    }


    private fun buildAttachment(): Attachment {
        return Attachment(
            id = UUID.randomUUID().toString(),
            objectId = "objectId",
            contentType = "contentType",
            description = "description",
            url = "url",
            isPrivate = true
        )
    }
}