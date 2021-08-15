package com.avall.ms.attachments.domain.port.input

import com.avall.ms.attachments.arch.stereotype.UseCase
import com.avall.ms.attachments.domain.model.Attachment

interface ICreateAttachmentUseCase : UseCase<ICreateAttachmentUseCase.Input, ICreateAttachmentUseCase.Output> {

    data class Input(
        var attachments: List<Attachment>
    )

    data class Output(
        var attachments: List<Attachment>
    )

}