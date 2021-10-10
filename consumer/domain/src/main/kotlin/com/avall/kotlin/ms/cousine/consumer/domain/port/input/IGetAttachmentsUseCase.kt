package com.avall.kotlin.ms.cousine.consumer.domain.port.input

import com.avall.kotlin.ms.cousine.consumer.arch.stereotype.Query
import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment

interface IGetAttachmentsUseCase :
    Query<IGetAttachmentsUseCase.Input, IGetAttachmentsUseCase.Output> {
    data class Input(
        val objectId: String
    )

    data class Output(
        val attachments:List<Attachment>
    )
}