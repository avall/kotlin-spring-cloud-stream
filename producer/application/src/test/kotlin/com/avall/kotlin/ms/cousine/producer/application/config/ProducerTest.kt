package com.avall.kotlin.ms.cousine.producer.application.config

import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.kafka.test.utils.KafkaTestUtils.getSingleRecord
import strikt.api.expectThat
import strikt.assertions.isEqualTo


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "logging.level.com.avall.kotlin.ms.cousine.producer=debug",
        "spring.profiles.active=test",
        "spring.jackson.property-naming-strategy=SNAKE_CASE",
        "spring.jackson.default-property-inclusion=non_null",

        "spring.cloud.stream. default-binder=kafka",
        "spring.cloud.stream.function.definition=producer",
        "spring.cloud.stream.kafka.binder.brokers=\${spring.embedded.kafka.brokers}",

        "spring.cloud.stream.bindings.producer-out-0.destination=command.create-documents",
        "spring.cloud.stream.bindings.producer-out-0.group=group.documents",

        "spring.cloud.stream.kafka.binder.producer-properties.key.deserializer=org.apache.kafka.common.serialization.StringDeserializer",
        "spring.cloud.stream.kafka.binder.producer-properties.value.deserializer=io.confluent.kafka.serializers.KafkaJsonDeserializer",

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
    topics = ["command.create-documents"]
)
@AutoConfigureTestDatabase
class ProducerTest {
    val TOPIC = "command.create-documents"

    @Autowired lateinit var embeddedKafka: EmbeddedKafkaBroker
    @Autowired lateinit var  createAttachmentUseCase: ISendAttachmentUseCase

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

        // then
        val consumer: Consumer<String, String> = buildConsumer(
            StringDeserializer::class.java.name,
            JsonDeserializer::class.java.name
        )

        embeddedKafka.consumeFromEmbeddedTopics(consumer, TOPIC)
        val record: ConsumerRecord<String, String> = getSingleRecord(consumer, TOPIC, 500)

        expectThat(record) {
            get { value() } isEqualTo "{\"id\":1}"
        }

    }


    private  fun <K, V>  buildConsumer(
        keyDeserializer: String,
        valueDeserializer: String
    ): Consumer<K, V> {
        // Use the procedure documented at https://docs.spring.io/spring-kafka/docs/2.2.4.RELEASE/reference/#embedded-kafka-annotation
        val consumerProps: MutableMap<String, Any> = KafkaTestUtils
            .consumerProps("testMetricsEncodedAsSent", "true", embeddedKafka)
        // Since we're pre-sending the messages to test for, we need to read from start of topic
        consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        // We need to match the ser/deser used in expected application config
        consumerProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = keyDeserializer
        consumerProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = valueDeserializer
        val consumerFactory = DefaultKafkaConsumerFactory<K, V>(consumerProps)
        return consumerFactory.createConsumer()
    }
}