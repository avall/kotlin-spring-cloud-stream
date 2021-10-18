package com.avall.kotlin.ms.cousine.consumer.application.config;

import com.avall.kotlin.ms.cousine.consumer.CommandPayload
import com.avall.kotlin.ms.cousine.consumer.application.mapper.toCreateUseCaseInput
import com.avall.kotlin.ms.cousine.consumer.arch.extensions.loggerFor
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.ICreateAttachmentUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer
import org.springframework.messaging.Message

@Configuration
class ConsumerConfig(
    private val createAttachmentUseCsse: ICreateAttachmentUseCase
) {
    private val log = loggerFor(javaClass)

    /**
     * Consumer to create an attachment in the CRM (salesforce)
     * @return Consumer<CommandPayload>
     */
    @Bean
    fun consumer():Consumer<Message<CommandPayload>> {
        return Consumer<Message<CommandPayload>> {
            log.info("consuming event {}", it.payload)// l get dispatched to DefaultDispatcher
            createAttachmentUseCsse.execute(it.payload.toCreateUseCaseInput())
        };
    }
}
