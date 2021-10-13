package com.avall.kotlin.ms.cousine.producer.application.config;

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.arch.extensions.loggerFor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.BlockingQueue
import java.util.function.Supplier


@Configuration
class ProducerConfig(
    private val blockingQueue: BlockingQueue<CommandPayload>
) {
    private val log = loggerFor(javaClass)

    /**
     * Consumer to create an attachment in the CRM (salesforce)
     * @return Consumer<CommandPayload>
     */
    @Bean
    open fun producer(): Supplier<CommandPayload> {
        return Supplier<CommandPayload> { blockingQueue.poll() }
    }
}
