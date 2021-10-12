package com.avall.kotlin.ms.cousine.producer.domain.usecase.attachment

import com.avall.kotlin.ms.cousine.producer.arch.extensions.loggerFor
import com.avall.kotlin.ms.cousine.producer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import com.avall.kotlin.ms.cousine.producer.domain.port.output.ISendAttachmentPort

@Interactor
class CreateAttachmentUseCase(private val saveAttachmentPort: ISendAttachmentPort) : ISendAttachmentUseCase {

    private val log = loggerFor(javaClass)

    override fun execute(input: ISendAttachmentUseCase.Input):ISendAttachmentUseCase.Output {
        saveAttachmentPort.execute(input.attachments)
        log.info("attachment saved into CRM {}", input.attachments)

        return ISendAttachmentUseCase.Output(input.attachments)
    }
}