package com.avall.kotlin.ms.cousine.consumer.domain.usecase.attachment

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IGetAttachmentPort

@Interactor
class GetAttachmentUseCase(private val getAttachmentPort: IGetAttachmentPort) : IGetAttachmentUseCase {
    override fun execute(input: IGetAttachmentUseCase.Input): IGetAttachmentUseCase.Output {
        return IGetAttachmentUseCase.Output(
            attachment = getAttachmentPort.get(input.id)
        )
    }
}