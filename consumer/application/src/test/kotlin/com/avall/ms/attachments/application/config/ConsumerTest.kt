package com.avall.ms.attachments.application.config

import com.avall.ms.attachments.CommandAttachment
import com.avall.ms.attachments.CommandPayload
import com.avall.ms.attachments.EXECUTE_CREATE_CRM_DOCUMENTS_COMMAND
import com.avall.ms.attachments.application.service.PublisherService
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import org.awaitility.Awaitility.waitAtMost
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.kafka.test.context.EmbeddedKafka
import java.util.concurrent.TimeUnit
import java.util.function.Consumer


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "logging.level.com.avall.ms.attachments=debug",
        "spring.profiles.active=test",
        "spring.jackson.property-naming-strategy=SNAKE_CASE",
        "spring.jackson.default-property-inclusion=non_null",

        "spring.cloud.stream. default-binder=kafka",
        "spring.cloud.stream.function.definition=execute-create-crm-documents-command",
        "spring.cloud.stream.kafka.binder.brokers=\${spring.embedded.kafka.brokers}",

        "spring.cloud.stream.bindings.execute-create-crm-documents-command-in-0.destination=command.comms.execute-create-crm-documents",
        "spring.cloud.stream.bindings.execute-create-crm-documents-command-in-0.destination=group.comms.crm-documents",

        "spring.cloud.stream.kafka.binder.consumer-properties.key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
        "spring.cloud.stream.kafka.binder.consumer-properties.value.deserializer=io.confluent.kafka.serializers.KafkaJsonDeserializer",

        "logging.level.org.springframework.kafka=warn",
        "logging.level.org.springframework.cloud=debug",
        "logging.level.org.springframework.integration=debug",
        "logging.level.kafka=warn",

        "spring.liquibase.enabled=false",
        "spring.jpa.database = h2",
        "spring.datasource.url=jdbc:hsqldb:mem:testdb",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.HSQLDialect",
        "spring.jpa.show-sql=true",
        "spring.jpa.hibernate.ddl-auto=create"
    ]
)
@EmbeddedKafka(
    partitions = 1,
    controlledShutdown = true,
    topics = ["command.comms.execute-create-crm-documents"]
)
@AutoConfigureTestDatabase
class ConsumerTest {
    @Autowired lateinit var publisher: PublisherService
    @Captor private val commandPayloadCaptor: ArgumentCaptor<CommandPayload>?=null
    @MockBean lateinit var  createAttachmentUseCase: ICreateAttachmentUseCase

    @MockBean(name = EXECUTE_CREATE_CRM_DOCUMENTS_COMMAND) lateinit var consumer: Consumer<CommandPayload>

    @Test
    fun Given_an_CommandPayload_When_send_to_topic_Then_consumed() {
        // Given
        val event: CommandPayload = CommandPayload(
            documents = listOf(
                CommandAttachment(
                    objectId = "objectId",
                    contentType = "contentType",
                    url = "path",
                    isPrivate = true,
                    description = "description"
                )
            )
        )

        //When
        publisher.send(event, EXECUTE_CREATE_CRM_DOCUMENTS_COMMAND+"-in-0")

        // then
        waitAtMost(5, TimeUnit.SECONDS)
            .untilAsserted {
                verify(consumer).accept(commandPayloadCaptor!!.capture())
                val captorValue: CommandPayload =
                    commandPayloadCaptor.getValue()
                //Check log level is correct
                assertAll(
                    {
                        assertEquals(
                            event.documents.get(0).description, captorValue.documents.get(0).description,
                            "objectId"
                        )
                    },
                    {
                        assertEquals(
                            event.documents.get(0).isPrivate, captorValue.documents.get(0).isPrivate,
                            "parentObjectName"
                        )
                    },
                    {
                        assertEquals(
                            event.documents.get(0).contentType, captorValue.documents.get(0).contentType,
                            "contentType"
                        )
                    },
                    {
                        assertEquals(
                            event.documents.get(0).url, captorValue.documents.get(0).url,
                            "fileName"
                        )
                    })
            }
    }
}
