package com.avall.kotlin.ms.cousine.producer.application.config

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.messaging.Message
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.util.function.Supplier


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "spring.profiles.active=test",
        "spring.jackson.property-naming-strategy=SNAKE_CASE",
        "spring.jackson.default-property-inclusion=non_null",

        "spring.cloud.stream.default-binder=kafka",
        "spring.cloud.stream.function.definition=producer",
        "spring.cloud.stream.kafka.binder.brokers=\${spring.embedded.kafka.brokers}",

        "spring.cloud.stream.bindings.producer-out-0.destination=command.create-documents",
        "spring.cloud.stream.bindings.producer-out-0.group=group.documents",

        "spring.cloud.stream.kafka.binder.producer-properties.key.serializer=org.apache.kafka.common.serialization.StringSerializer",
        "spring.cloud.stream.kafka.binder.producer-properties.value.serializer=org.springframework.kafka.support.serializer.JsonSerializer",

        "logging.level.com.avall.kotlin.ms.cousine.producer=debug",
        "logging.level.org.springframework.kafka=warn",
        "logging.level.org.springframework.cloud=debug",
        "logging.level.org.springframework.integration=debug",
        "logging.level.kafka=warn",
    ]
)
@EmbeddedKafka(
    partitions = 1,
    controlledShutdown = true,
    topics = ["command.create-documents"]
)
class ChannelTest {

    @Autowired lateinit var createAttachmentUseCase: ISendAttachmentUseCase
    @Autowired lateinit var producer: Supplier<Message<CommandPayload>>

    @Test
    fun Given_an_CommandPayload_When_send_to_topic_Then_enqueued() {
        // Given
        val attachments: List<Attachment> = listOf(
                Attachment(
                    objectId = "objectId",
                    objectName = "objectName",
                    contentType = "contentType",
                    url = "path",
                    isPrivate = true,
                    description = "description"
                )
            )

        //When
        createAttachmentUseCase.execute(ISendAttachmentUseCase.Input(attachments))

        //Then
        expectThat(producer.get()) {
            get { payload.documents.get(0).objectName } isEqualTo "objectName"
        }
    }
}
