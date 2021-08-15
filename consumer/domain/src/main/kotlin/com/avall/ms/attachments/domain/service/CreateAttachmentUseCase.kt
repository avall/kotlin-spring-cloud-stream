package com.avall.ms.attachments.domain.service

import com.avall.ms.attachments.arch.extensions.loggerFor
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import com.avall.ms.attachments.domain.port.output.ISaveAttachmentPort

@Interactor
class CreateAttachmentUseCase(private val saveAttachmentPort: ISaveAttachmentPort) : ICreateAttachmentUseCase {

    private val log = loggerFor(javaClass)

    override fun execute(input: ICreateAttachmentUseCase.Input):ICreateAttachmentUseCase.Output {
        val a = saveAttachmentPort.store(input.attachments)
        log.info("attachment saved into CRM {}", a)

        return ICreateAttachmentUseCase.Output(a)
    }
}