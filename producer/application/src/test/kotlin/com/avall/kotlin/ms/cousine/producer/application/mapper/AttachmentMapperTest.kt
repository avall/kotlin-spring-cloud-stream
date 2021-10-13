package com.avall.kotlin.ms.cousine.producer.application.mapper

import com.avall.kotlin.ms.cousine.producer.CommandAttachment
import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentRequest
import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExtendWith(MockitoExtension::class)
class AttachmentMapperTest {

    @Test
    fun `Give correct fields When CreateAttachmentWrapperRequest is converted to CreateUseCaseInput`() {
        //Give
        val createAttachmentWrapperRequest = CreateAttachmentWrapperRequest(listOf(getCreateAttachmentRequest()))

        //When
        val useCaseInput = createAttachmentWrapperRequest.toCreateUseCaseInput()

        // then
        expectThat(useCaseInput.attachments[0]) {
            get { id } isEqualTo null
            get { contentType } isEqualTo createAttachmentWrapperRequest.documents.get(0).contentType
            get { objectId } isEqualTo createAttachmentWrapperRequest.documents.get(0).objectId
            get { url } isEqualTo createAttachmentWrapperRequest.documents.get(0).url
            get { description } isEqualTo createAttachmentWrapperRequest.documents.get(0).description
            get { isPrivate } isEqualTo createAttachmentWrapperRequest.documents.get(0).isPrivate
        }
    }

    @Test
    fun `Give correct fields When CommandPayload is converted to CreateUseCaseInput`() {
        //Give
        val commandPayload = CommandPayload(listOf(getCommandAttachment()))

        //When
        val useCaseInput = commandPayload.toCreateUseCaseInput()

        // then
        expectThat(useCaseInput.attachments[0]) {
            get { id } isEqualTo null
            get { objectId } isEqualTo commandPayload.documents.get(0).objectId
            get { contentType } isEqualTo commandPayload.documents.get(0).contentType
            get { url } isEqualTo commandPayload.documents.get(0).url
            get { description } isEqualTo commandPayload.documents.get(0).description
            get { isPrivate } isEqualTo commandPayload.documents.get(0).isPrivate
        }
    }


    /**
     * Factory to create an attachment
     * @return Attachment
     */
    private fun getAttachment(): Attachment {
        return Attachment(
            id = "UUID",
            objectId = "objectId",
            objectName = "objectName",
            contentType = "contentType",
            url = "url",
            description = "description",
            isPrivate = true
        )
    }

    private fun getCreateAttachmentRequest(): CreateAttachmentRequest {
        return CreateAttachmentRequest(
            contentType = "contentType",
            url = "url",
            objectId = "objectId",
            objectName = "objectName",
            description = "description",
            isPrivate = true
        )
    }

    private fun getCommandAttachment(): CommandAttachment {
        return CommandAttachment(
            objectId = "objectId",
            objectName = "objectName",
            contentType = "contentType",
            url = "url",
            description = "description",
            isPrivate = true
        )
    }

}