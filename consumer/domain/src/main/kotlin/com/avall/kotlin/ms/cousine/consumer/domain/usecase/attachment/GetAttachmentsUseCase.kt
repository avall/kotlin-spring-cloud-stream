package com.avall.kotlin.ms.cousine.consumer.domain.usecase.attachment

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentsUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IGetAttachmentsPort


@Interactor
class GetAttachmentsUseCase(private val getAttachmentsPort: IGetAttachmentsPort) : IGetAttachmentsUseCase {
    override fun execute(input: IGetAttachmentsUseCase.Input) : IGetAttachmentsUseCase.Output {
        return IGetAttachmentsUseCase.Output(getAttachmentsPort.findByObjectId(input.objectId))
    }
}