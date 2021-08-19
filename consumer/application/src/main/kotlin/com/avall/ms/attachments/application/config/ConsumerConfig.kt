package com.avall.ms.attachments.application.config;

import com.avall.ms.attachments.CommandPayload
import com.avall.ms.attachments.EXECUTE_CREATE_ATTACHMENTS_COMMAND
import com.avall.ms.attachments.application.mapper.toCreateUseCaseInput
import com.avall.ms.attachments.arch.extensions.loggerFor
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class ConsumerConfig(
    private val createAttachmentUseCsse: ICreateAttachmentUseCase
) {
    private val log = loggerFor(javaClass)

    /**
     * Consumer to create an attachment in the CRM (salesforce)
     * @return Consumer<CommandPayload>
     */
    @Bean(EXECUTE_CREATE_ATTACHMENTS_COMMAND)
    fun consumer():Consumer<CommandPayload> {
        return Consumer<CommandPayload> {
            log.info("consuming event {}", it)// l get dispatched to DefaultDispatcher
            createAttachmentUseCsse.execute(it.toCreateUseCaseInput())
        };
    }
}
