package com.avall.ms.attachments.infrastructure.repository

import com.avall.ms.attachments.infrastructure.database.AttachmentDb
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@DataJpaTest(properties = ["show_sql = true"])
@ExtendWith(value = [SpringExtension::class])
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = [
    "spring.liquibase.enabled=false"
])
class AttachmentRepositoryTest {

    @Autowired
    lateinit var attachmentRepository: AttachmentRepository

    @Test
    fun `Given a new Attachment When the Attachment stored in database Then you can retrieve using findById`() {
        // Given
        var attachment = getNewAttachment()

        // When
        attachment = this.attachmentRepository.save(attachment)

        // Then
        expectThat(this.attachmentRepository.findById(attachment.id.toString()).get())
        {
            get { id } isEqualTo attachment.id
            get { objectId } isEqualTo attachment.objectId
            get { contentType } isEqualTo attachment.contentType
            get { url } isEqualTo attachment.url
            get { isPrivate } isEqualTo attachment.isPrivate
            get { description } isEqualTo attachment.description
        }
    }

    @Test
    fun `Given a saved Attachment When the Attachment is deleted in database Then is not found`() {
        // Given
        var attachment: AttachmentDb = getNewAttachment()
        attachment = this.attachmentRepository.save(attachment)

        // When
        this.attachmentRepository.delete(attachment)

        // Then
        expectThat(this.attachmentRepository.findById(attachment.id.toString()).isPresent).isEqualTo(false)
    }


    private fun getNewAttachments(): List<AttachmentDb> {
        return listOf(getNewAttachment(), getNewAttachment())
    }

    private fun getAttachment(): AttachmentDb {
        return AttachmentDb(
            id = "UUID",
            objectId = "objectId",
            contentType = "contentType",
            url = "url",
            description = "description",
            isPrivate = true
        )
    }

    private fun getNewAttachment(): AttachmentDb {
        var attachmentEntity = getAttachment()
        attachmentEntity.id = null
        return attachmentEntity
    }
}