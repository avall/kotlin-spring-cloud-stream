package com.avall.ms.attachments.domain.usecase.attachment

import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.port.input.IGetAttachmentsUseCase
import com.avall.ms.attachments.domain.port.output.IGetAttachmentsPort


@Interactor
class GetAttachmentsUseCase(private val getAttachmentsPort: IGetAttachmentsPort) : IGetAttachmentsUseCase {
    override fun execute(input: IGetAttachmentsUseCase.Input) : IGetAttachmentsUseCase.Output {
        return IGetAttachmentsUseCase.Output(getAttachmentsPort.findByParentId(input.parentId))
    }
}