package com.avall.kotlin.ms.cousine.producer.application.config

import com.avall.kotlin.ms.cousine.producer.CommandAttachment
import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.MsProducerApplication
import com.avall.kotlin.ms.cousine.producer.application.service.PublisherService
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.KeyValue
import org.awaitility.Awaitility
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.kafka.test.utils.KafkaTestUtils.getSingleRecord
import org.springframework.util.Assert
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
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
class ProducerTest {
    val TOPIC = "command.create-documents"
    val GROUP = "group.documents"

    @Autowired lateinit var blockingQueue: BlockingQueue<CommandPayload>
    @Autowired lateinit var publisher: PublisherService
    @Autowired lateinit var embeddedKafka: EmbeddedKafkaBroker
    @Autowired lateinit var  createAttachmentUseCase: ISendAttachmentUseCase
//    @MockBean(name = "producer") var producer: Supplier<CommandPayload> = Supplier<CommandPayload> {
//        blockingQueue.poll()
//    }


    @Test
    fun Given_an_CommandPayload_When_send_to_topic_Then_enqueued() {

//        blockingQueue.offer(
//            CommandPayload(
//            documents = listOf(
//                CommandAttachment(
//                    objectId = "objectId",
//                    objectName = "objectName",
//                    contentType = "contentType",
//                    url = "path",
//                    isPrivate = true,
//                    description = "description"
//                )
//            )
//        )
//        )
//        val pp = blockingQueue.poll()

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

        val consumer: Consumer<String, CommandPayload> = buildConsumer(
            embeddedKafka,
            GROUP,
            StringDeserializer::class.java.name,
            JsonDeserializer::class.java.name
        )

        embeddedKafka.consumeFromEmbeddedTopics(consumer, TOPIC)


        //When
        createAttachmentUseCase.execute(ISendAttachmentUseCase.Input(attachments))

//
//        publisher.send(
//            CommandPayload(
//                documents = listOf(
//                    CommandAttachment(
//                        objectId = "objectId",
//                        objectName = "objectName",
//                        contentType = "contentType",
//                        url = "path",
//                        isPrivate = true,
//                        description = "description"
//                    )
//                ))
//            , "producer-out-0")


        val records = KafkaTestUtils.getRecords<String, CommandPayload>(consumer, 1000)

        // then
        expectThat(records.records(TOPIC).elementAt(0).value()) {
            get { documents.get(0).objectName } isEqualTo "objectName"
        }

//        val record: ConsumerRecord<String, CommandPayload> = getSingleRecord(consumer, TOPIC, 1000)

//        // then
//        expectThat(records) {
//            get { value().documents.get(0).objectName } isEqualTo "{\"id\":1}"
//        }



        // then
//        verify(producer,  times(1)).get()


    }


    private  fun <K, V>  buildConsumer(
        embeddedKafka: EmbeddedKafkaBroker,
        group: String,
        keyDeserializer: String,
        valueDeserializer: String
    ): Consumer<K, V> {
        // Use the procedure documented at https://docs.spring.io/spring-kafka/docs/2.2.4.RELEASE/reference/#embedded-kafka-annotation
        val consumerProps: MutableMap<String, Any> = KafkaTestUtils
            .consumerProps(group, "true", embeddedKafka)
        // Since we're pre-sending the messages to test for, we need to read from start of topic
        consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        // We need to match the ser/deser used in expected application config
        consumerProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = keyDeserializer
        consumerProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = valueDeserializer
        consumerProps[JsonDeserializer.TRUSTED_PACKAGES] = "*";

        return DefaultKafkaConsumerFactory<K, V>(consumerProps).createConsumer()
    }
}
