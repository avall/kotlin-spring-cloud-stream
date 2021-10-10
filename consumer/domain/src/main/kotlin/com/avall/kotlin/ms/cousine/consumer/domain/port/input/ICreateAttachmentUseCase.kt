package com.avall.kotlin.ms.cousine.consumer.domain.port.input

import com.avall.kotlin.ms.cousine.consumer.arch.stereotype.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment

interface ICreateAttachmentUseCase : UseCase<ICreateAttachmentUseCase.Input, ICreateAttachmentUseCase.Output> {

    data class Input(
        var attachments: List<Attachment>
    )

    data class Output(
        var attachments: List<Attachment>
    )

}