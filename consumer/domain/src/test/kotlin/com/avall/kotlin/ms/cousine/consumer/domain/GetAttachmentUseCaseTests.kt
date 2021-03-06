package com.avall.kotlin.ms.cousine.consumer.domain

import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IGetAttachmentPort
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.attachment.GetAttachmentUseCase
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
            get { objectId } isEqualTo attachment.objectId
            get { contentType } isEqualTo attachment.contentType
            get { url } isEqualTo attachment.url
            get { description } isEqualTo attachment.description
        }
    }

    fun attachment():Attachment {
        return Attachment(
            id = "UUID",
            objectId = "objectId",
            objectName = "objectName",
            contentType = "contentType",
            url = "path",
            description = "description"
        )
    }
}