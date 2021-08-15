package com.avall.ms.attachments.domain.service

import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.port.input.IGetAttachmentUseCase
import com.avall.ms.attachments.domain.port.output.IGetAttachmentPort

@Interactor
class GetAttachmentUseCase(private val getAttachmentPort: IGetAttachmentPort) : IGetAttachmentUseCase {
    override fun execute(input: IGetAttachmentUseCase.Input): IGetAttachmentUseCase.Output {
        return IGetAttachmentUseCase.Output(
            attachment = getAttachmentPort.get(input.id)
        )
    }
}