package com.avall.kotlin.ms.cousine.producer.application.config;

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.arch.extensions.loggerFor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.concurrent.BlockingQueue
import java.util.function.Supplier

@Configuration
class ProducerConfig(
    private val blockingQueuePayload: BlockingQueue<Message<CommandPayload>>
) {
    private val log = loggerFor(javaClass)

    /**
     * Producer to send an attachment to the consumer.
     * @return Consumer<CommandPayload>
     */
    @Bean
    open fun producer(): Supplier<Message<CommandPayload>> {
        return Supplier<Message<CommandPayload>> {
            blockingQueuePayload.poll()
        }
    }
}
