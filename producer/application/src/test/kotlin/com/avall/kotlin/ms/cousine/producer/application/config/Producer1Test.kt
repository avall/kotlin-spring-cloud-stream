package com.avall.kotlin.ms.cousine.producer.application.config

import com.avall.kotlin.ms.cousine.producer.CommandAttachment
import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.MsProducerApplication
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.junit.jupiter.SpringExtension
import strikt.api.expectThat
import strikt.assertions.isEqualTo


@ExtendWith(SpringExtension::class)
@EmbeddedKafka(
    partitions = 1,
    controlledShutdown = true,
    topics = ["command.create-documents"]
)
class Producer1Test {
    val TOPIC = "command.create-documents"

    @Autowired private lateinit var embeddedKafka: EmbeddedKafkaBroker

    @Test
    fun testStreamToTableBiConsumer() {
        val app = SpringApplication(MsProducerApplication::class.java)
        app.webApplicationType = WebApplicationType.NONE
        val consumer: Consumer<String, CommandPayload>
        val consumerProps = KafkaTestUtils.consumerProps(
            "group.documents",
            "true", embeddedKafka
        )
        consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        consumerProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        consumerProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        consumerProps[JsonDeserializer.TRUSTED_PACKAGES] = "*";

        val cf = DefaultKafkaConsumerFactory<String, CommandPayload>(consumerProps)
        consumer = cf.createConsumer()
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, TOPIC)
        try {
            app.run(
                "--server.port=0",
                "--spring.jmx.enabled=false",
                "--spring.cloud.stream.bindings.producer-out-0.destination=command.create-documents",
                "--spring.cloud.stream.bindings.producer-out-0.group=group.documents",
                "--spring.cloud.stream.kafka.streams.binder.configuration.commit.interval.ms=10000",
                "--spring.cloud.stream.kafka.streams.binder.brokers=" + embeddedKafka.brokersAsString
            ).use { ignored ->
                val senderProps1 =
                    KafkaTestUtils.producerProps(embeddedKafka)
                senderProps1[org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] =
                    StringSerializer::class.java
                senderProps1[org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] =
                    JsonSerializer::class.java
                val pf1 =
                    DefaultKafkaProducerFactory<String, CommandPayload>(senderProps1)
                val template1 =
                    KafkaTemplate(pf1, true)
                template1.defaultTopic = TOPIC
                    template1.sendDefault("1",
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
                            ))
                    )

                val records = KafkaTestUtils.getRecords<String, CommandPayload>(consumer)

                // then
                expectThat(records.records(TOPIC).elementAt(0).value()) {
                    get { documents.get(0).objectName } isEqualTo "objectName"
                }
            }
        } finally {
            consumer.close()
        }
    }
}
