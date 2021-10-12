package com.avall.kotlin.ms.cousine.producer.config

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue


@Configuration
class BlockingQueueConfig {
    @Bean
    fun blockingQueuePayload(): BlockingQueue<CommandPayload> {
        return LinkedBlockingQueue()
    }

}
