package com.avall.ms.attachments.domain

import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.input.IGetAttachmentUseCase
import com.avall.ms.attachments.domain.port.output.IGetAttachmentPort
import com.avall.ms.attachments.domain.usecase.GetAttachmentUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class GetAttachmentUseCaseTests {
    @Mock lateinit var getAttachmentPort: IGetAttachmentPort
    @InjectMocks lateinit var useCase: GetAttachmentUseCase

    @Test
    fun `When Calling useCase_execute Then GetAttachmentPort is called`() {
        // Given
        val attachment = attachment()
        `when`(getAttachmentPort.get(any())).thenReturn(attachment)

        val input = IGetAttachmentUseCase.Input("")

        // When: Calling useCase.execute method passing null as parameter
        val result = useCase.execute(input)!!

        // Then: Should return null
        verify(getAttachmentPort, times(1)).get(any())
        expectThat(result.attachment) {
            get { id } isEqualTo attachment.id
            get { parentId } isEqualTo attachment.parentId
            get { parentObjectName } isEqualTo attachment.parentObjectName
            get { contentType } isEqualTo attachment.contentType
            get { docType } isEqualTo attachment.docType
            get { type } isEqualTo attachment.type
            get { path } isEqualTo attachment.path
            get { fileName } isEqualTo attachment.fileName
            get { description } isEqualTo attachment.description
        }
    }

    fun attachment():Attachment {
        return Attachment(
            id = "UUID",
            parentId = "parentId",
            parentObjectName = "parentObjectName",
            contentType = "contentType",
            docType = "docType",
            type = "type",
            path = "path",
            fileName = "fileName",
            description = "description"
        )
    }
}