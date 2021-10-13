package com.avall.kotlin.ms.cousine.producer.domain

import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import com.avall.kotlin.ms.cousine.producer.domain.port.output.ISendAttachmentPort
import com.avall.kotlin.ms.cousine.producer.domain.usecase.attachment.CreateAttachmentUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class CreateAttachmentUseCaseTests {
    @Mock lateinit var sendAttachmentPort: ISendAttachmentPort
    @InjectMocks lateinit var useCase: CreateAttachmentUseCase

    @Test
    fun `When Calling useCase_execute Then StoreAttachmentPort is called`() {
        // Given
        doNothing().`when`(sendAttachmentPort).execute(any())

        val input = ISendAttachmentUseCase.Input(
            listOf<Attachment>(
                attachment()
            )
        )

        // When: Calling useCase.execute method passing null as parameter
        useCase.execute(input)

        // Then: Should return null
        verify(sendAttachmentPort, times(1)).execute(any())

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