package com.avall.ms.attachments.apicontract

import com.avall.ms.attachments.application.config.GlobalExceptionHandler
import com.avall.ms.attachments.application.controller.AttachmentsController
import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentsUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import io.restassured.config.EncoderConfig
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.quality.Strictness
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.setup.MockMvcBuilders


// https://cloud.spring.io/spring-cloud-contract/reference/html/project-features.html#feature-webflux
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
open class AttachmentsBase {

    var createAttachmentUseCase: ICreateAttachmentUseCase = mock {
        on { execute(any()) } doAnswer { ICreateAttachmentUseCase.Output(attachments()) }
    }
    var getAttachmentUseCase: IGetAttachmentUseCase = mock {
        on { execute(any()) } doReturn IGetAttachmentUseCase.Output(attachment())
    }
    var getAttachmentsUseCase: IGetAttachmentsUseCase = mock {
        on { execute(any()) } doReturn IGetAttachmentsUseCase.Output(attachments())
    }
    var attachmentsController: AttachmentsController = AttachmentsController(
        createAttachmentUseCase, getAttachmentUseCase, getAttachmentsUseCase
    )

    @BeforeAll
    fun setup() {
        val encoderConfig =
            EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)

        val standaloneMockMvcBuilder = MockMvcBuilders
            .standaloneSetup(attachmentsController)
            .setMessageConverters(snakeCaseConverter())
            .setControllerAdvice(GlobalExceptionHandler())
            .setCustomArgumentResolvers(PageableHandlerMethodArgumentResolver())

        RestAssuredMockMvc.config = RestAssuredMockMvcConfig().encoderConfig(encoderConfig)
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder)
    }

    private fun attachments(): List<Attachment> {
        return listOf(attachment(), attachment())
    }

    private fun attachment(): Attachment {
        return Attachment(
            id = "7d2c830c-5e62-11eb-ae93-0242ac130002",
            objectId = "objectId",
            contentType = "contentType",
            url = "path",
            description = "description",
            isPrivate = false
        )

    }

    private fun snakeCaseConverter(): MappingJackson2HttpMessageConverter? {
        val mappingJackson2HttpMessageConverter = MappingJackson2HttpMessageConverter()
        mappingJackson2HttpMessageConverter.objectMapper =
            ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        return mappingJackson2HttpMessageConverter
    }

}