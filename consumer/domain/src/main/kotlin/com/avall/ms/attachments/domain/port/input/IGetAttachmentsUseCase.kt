package com.avall.ms.attachments.domain.port.input

import com.avall.ms.attachments.arch.stereotype.Query
import com.avall.ms.attachments.domain.model.Attachment

interface IGetAttachmentsUseCase :
    Query<IGetAttachmentsUseCase.Input, IGetAttachmentsUseCase.Output> {
    data class Input(
        val parentId: String
    )

    data class Output(
        val attachments:List<Attachment>
    )
}