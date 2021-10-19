package com.avall.kotlin.ms.cousine.producer

import com.avall.kotlin.ms.cousine.producer.config.BlockingQueueConfig
import com.avall.kotlin.ms.cousine.producer.config.ProducerConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.util.concurrent.BlockingQueue
import java.util.function.Supplier


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = [BlockingQueueConfig::class, ProducerConfig::class],
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
class BlockingQueueTest {

    @Autowired lateinit var blockingQueuePayload: BlockingQueue<Message<CommandPayload>>
    @Autowired lateinit var producer: Supplier<Message<CommandPayload>>

    @Test
    open fun test() {
        blockingQueuePayload.offer(
            MessageBuilder.withPayload(
                CommandPayload(
                    documents = listOf(
                        CommandAttachment(
                            objectId = "objectId",
                            objectName = "objectName",
                            contentType = "contentType",
                            url = "path",
                            isPrivate = true,
                            description = "description"
                        )
                    )
                )
            )
                .setHeader("x-tenant", "xx")
                .build()
        )

        expectThat(producer.get()) {
            get { payload.documents.get(0).objectName } isEqualTo "objectName"
        }
    }
}