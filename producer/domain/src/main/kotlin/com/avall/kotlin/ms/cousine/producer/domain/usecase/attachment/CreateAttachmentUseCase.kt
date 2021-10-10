package com.avall.kotlin.ms.cousine.producer.domain.usecase.attachment

import com.avall.kotlin.ms.cousine.producer.arch.extensions.loggerFor
import com.avall.kotlin.ms.cousine.producer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ICreateAttachmentUseCase
import com.avall.kotlin.ms.cousine.producer.domain.port.output.ISaveAttachmentPort

@Interactor
class CreateAttachmentUseCase(private val saveAttachmentPort: ISaveAttachmentPort) : ICreateAttachmentUseCase {

    private val log = loggerFor(javaClass)

    override fun execute(input: ICreateAttachmentUseCase.Input):ICreateAttachmentUseCase.Output {
        val a = saveAttachmentPort.store(input.attachments)
        log.info("attachment saved into CRM {}", a)

        return ICreateAttachmentUseCase.Output(a)
    }
}