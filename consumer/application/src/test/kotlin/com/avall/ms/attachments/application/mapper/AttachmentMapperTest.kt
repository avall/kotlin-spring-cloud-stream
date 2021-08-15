package com.avall.ms.attachments.application.mapper

import com.avall.ms.attachments.CommandAttachment
import com.avall.ms.attachments.CommandPayload
import com.avall.ms.attachments.api.dto.request.CreateAttachmentRequest
import com.avall.ms.attachments.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExtendWith(MockitoExtension::class)
class AttachmentMapperTest {

    @Test
    fun `Given String buildGetAttachmentQuery returns IGetAttachmentUseCase Input`() {
        // Given
        val given = "id"

        // when
        val result = AttachmentsMapper.buildGetAttachmentQuery(given)

        // then
        expectThat(result) {
            get { id } isEqualTo given
        }
    }


    @Test
    fun `Given String buildGetAttachmentsQuery returns IGetAttachmentUseCase Input`() {
        // Given
        val given = "parentId"

        // when
        val result = AttachmentsMapper.buildGetAttachmentsQuery(given)

        // then
        expectThat(result) {
            get { parentId } isEqualTo given
        }
    }


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
            get { contentType } isEqualTo commandPayload.documents.get(0).contentType
            get { url } isEqualTo commandPayload.documents.get(0).url
            get { description } isEqualTo commandPayload.documents.get(0).description
            get { isPrivate } isEqualTo commandPayload.documents.get(0).isPrivate
        }
    }

    @Test
    fun `Give correct fields When Attachment is converted to GetAttachmentResponse`() {
        //Give
        val attachment = getAttachment()

        //When
        val getAttachmentResponse = attachment.toGetAttachmentResponse()

        // then
        expectThat(getAttachmentResponse) {
            get { id } isEqualTo getAttachmentResponse.id
            get { contentType } isEqualTo getAttachmentResponse.contentType
            get { url } isEqualTo getAttachmentResponse.url
            get { description } isEqualTo getAttachmentResponse.description
            get { isPrivate } isEqualTo getAttachmentResponse.isPrivate
        }
    }

    @Test
    fun `Give correct fields When Attachment is converted to GetAttachmentsResponse`() {
        //Give
        val attachment = getAttachment()

        //When
        val getAttachmentsResponse = attachment.toGetAttachmentResponse()

        // then
        expectThat(getAttachmentsResponse) {
            get { id } isEqualTo getAttachmentsResponse.id
            get { contentType } isEqualTo getAttachmentsResponse.contentType
            get { url } isEqualTo getAttachmentsResponse.url
            get { description } isEqualTo getAttachmentsResponse.description
            get { isPrivate } isEqualTo getAttachmentsResponse.isPrivate
        }
    }

    @Test
    fun  `Give CreateAttachmentUseCase Output is converted to CreateAttachmentWrapperResponse`() {
        //Give
        val output = ICreateAttachmentUseCase.Output(listOf(getAttachment()))

        //when
        val result = output.toCreateAttachmentWrapperResponse()

        // then
        expectThat(result.documents[0]) {
            get { id } isEqualTo output.attachments.get(0).id
            get { contentType } isEqualTo output.attachments.get(0).contentType
            get { url } isEqualTo output.attachments.get(0).url
            get { description } isEqualTo output.attachments.get(0).description
            get { isPrivate } isEqualTo output.attachments.get(0).isPrivate
        }

    }

    /**
     * Factory to create an attachment
     * @return Attachment
     */
    private fun getAttachment(): Attachment {
        return Attachment(
            id = "UUID",
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
            description = "description",
            isPrivate = true
        )
    }

    private fun getCommandAttachment(): CommandAttachment {
        return CommandAttachment(
            contentType = "contentType",
            url = "url",
            description = "description",
            isPrivate = true
        )
    }

}