package com.avall.kotlin.ms.cousine.consumer.domain

import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentsUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IGetAttachmentsPort
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.attachment.GetAttachmentsUseCase
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
class GetAttachmentsUseCaseTests {
    @Mock lateinit var getAttachmentsPort: IGetAttachmentsPort
    @InjectMocks lateinit var useCase: GetAttachmentsUseCase

    @Test
    fun `When Calling useCase_execute Then GetAttachmentsPort is called`() {
        // Given
        val attachment = attachment()
        `when`(getAttachmentsPort.findByObjectId(any())).thenReturn(listOf(attachment))

        val input = IGetAttachmentsUseCase.Input("")

        // When: Calling useCase.execute method passing null as parameter
        val result = useCase.execute(input)

        // Then: Should return null
        verify(getAttachmentsPort, times(1)).findByObjectId(any())
        expectThat(result.attachments.get(0)) {
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