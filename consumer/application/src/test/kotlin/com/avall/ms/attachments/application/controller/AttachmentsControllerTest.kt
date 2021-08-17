package com.avall.ms.attachments.application.controller

import com.avall.ms.attachments.api.dto.request.CreateAttachmentRequest
import com.avall.ms.attachments.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.test.context.junit.jupiter.SpringExtension
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(SpringExtension::class)
class AttachmentsControllerTest {

    @Mock lateinit var createAttachmentUseCase: ICreateAttachmentUseCase
    @Mock lateinit var getAttachmentUseCase: IGetAttachmentUseCase
    @Mock lateinit var getAttachmentsUseCase: IGetAttachmentsUseCase

    @InjectMocks lateinit var attachmentsController: AttachmentsController
    @BeforeEach fun setUp() {}

    @Test
    fun `Give correct fields and verify methods When create Attachment then runs correctly`() {
        //Give
        val AttachmentRequest = buildAttachmentRequest()
        val attachment = getAttachment()

        //When
        `when`(createAttachmentUseCase.execute(any())).thenReturn(
            ICreateAttachmentUseCase.Output(listOf(attachment))
        )

        //Then
        val result = attachmentsController.createAttachment(AttachmentRequest)

        verify(createAttachmentUseCase, times(1)).execute(any())
        expectThat(result.documents.get(0)) {
            get { id } isEqualTo attachment.id
            get { contentType } isEqualTo attachment.contentType
            get { url } isEqualTo attachment.url
        }

    }

    @Test
    fun `Give correct fields and verify methods When Get Attachments by parentId`() {
        //Give
        val attachment = getAttachment()

        //When
        `when`(getAttachmentsUseCase.execute(any())).thenReturn(
            IGetAttachmentsUseCase.Output(
                listOf(
                    attachment
                )
            )
        )

        // then
        val result = attachmentsController.getAttachments("parentId")

        verify(getAttachmentsUseCase, times(1)).execute(any())

        expectThat(result.get(0)) {
            get { id } isEqualTo attachment.id
            get { contentType } isEqualTo attachment.contentType
            get { url } isEqualTo attachment.url
        }
    }


    @Test
    fun `Give correct fields and verify methods When Get Attachment by id`() {
        //Give
        val attachment = getAttachment()

        //When
        `when`(getAttachmentUseCase.execute(any())).thenReturn(
            IGetAttachmentUseCase.Output(attachment)
        )

        // then
        val result = attachmentsController.getAttachmentById("")

        verify(getAttachmentUseCase, times(1)).execute(any())

        expectThat(result) {
            get { id } isEqualTo attachment.id
            get { contentType } isEqualTo attachment.contentType
            get { url } isEqualTo attachment.url
        }
    }

    private fun getAttachment() = Attachment(
        id = "UUID",
        parentId = "parentId",
        contentType = "contentType",
        url = "path",
        description = "description"
    )

    private fun buildAttachmentRequest(): CreateAttachmentWrapperRequest {
        return CreateAttachmentWrapperRequest(
                    listOf(
                        CreateAttachmentRequest(
                            contentType = "contentType",
                            parentId = "parentId",
                            description = "description",
                            url = "path",
                            isPrivate = true
                        )
                    )
                )
    }
}