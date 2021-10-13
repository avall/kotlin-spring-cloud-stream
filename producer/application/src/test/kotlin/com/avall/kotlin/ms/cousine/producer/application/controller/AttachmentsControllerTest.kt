package com.avall.kotlin.ms.cousine.producer.application.controller

import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentRequest
import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
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

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(SpringExtension::class)
class AttachmentsControllerTest {

    @Mock lateinit var createAttachmentUseCase: ISendAttachmentUseCase

    @InjectMocks lateinit var attachmentsController: AttachmentsController
    @BeforeEach fun setUp() {}

    @Test
    fun `Give correct fields and verify methods When create Attachment then runs correctly`() {
        //Give
        val AttachmentRequest = buildAttachmentRequest()
        val attachment = getAttachment()

        //When
        `when`(createAttachmentUseCase.execute(any())).thenReturn(
            ISendAttachmentUseCase.Output(listOf(attachment))
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


    private fun getAttachment() = Attachment(
        id = "UUID",
        objectId = "objectId",
        objectName = "objectName",
        contentType = "contentType",
        url = "path",
        description = "description"
    )

    private fun buildAttachmentRequest(): CreateAttachmentWrapperRequest {
        return CreateAttachmentWrapperRequest(
                    listOf(
                        CreateAttachmentRequest(
                            contentType = "contentType",
                            objectId = "objectId",
                            objectName = "objectName",
                            description = "description",
                            url = "path",
                            isPrivate = true
                        )
                    )
                )
    }
}