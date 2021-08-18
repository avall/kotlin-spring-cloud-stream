package com.avall.ms.attachments.domain

import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import com.avall.ms.attachments.domain.port.output.ISaveAttachmentPort
import com.avall.ms.attachments.domain.usecase.attachment.CreateAttachmentUseCase
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class CreateAttachmentUseCaseTests {
    @Mock lateinit var saveAttachmentPort: ISaveAttachmentPort
    @InjectMocks lateinit var useCase: CreateAttachmentUseCase

    @Test
    fun `When Calling useCase_execute Then StoreAttachmentPort is called`() {
        // Given
        `when`(saveAttachmentPort.store(any())).thenReturn(listOf(attachment()))

        val input = ICreateAttachmentUseCase.Input(
            listOf<Attachment>(
                attachment()
            )
        )

        // When: Calling useCase.execute method passing null as parameter
        useCase.execute(input)

        // Then: Should return null
        verify(saveAttachmentPort, times(1)).store(any())

    }

    fun attachment():Attachment {
        return Attachment(
            id = "UUID",
            objectId = "objectId",
            contentType = "contentType",
            url = "path",
            description = "description"
        )
    }
}