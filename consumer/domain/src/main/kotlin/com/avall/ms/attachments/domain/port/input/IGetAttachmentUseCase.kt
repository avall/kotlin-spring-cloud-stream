package com.avall.ms.attachments.domain.port.input

import com.avall.ms.attachments.arch.stereotype.Query
import com.avall.ms.attachments.domain.model.Attachment

interface IGetAttachmentUseCase : Query<IGetAttachmentUseCase.Input, IGetAttachmentUseCase.Output?> {
    data class Input(val id: String)
    data class Output(val attachment: Attachment)
}