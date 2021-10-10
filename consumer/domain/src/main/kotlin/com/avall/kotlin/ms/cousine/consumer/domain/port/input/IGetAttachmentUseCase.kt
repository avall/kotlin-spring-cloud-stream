package com.avall.kotlin.ms.cousine.consumer.domain.port.input

import com.avall.kotlin.ms.cousine.consumer.arch.stereotype.Query
import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment

interface IGetAttachmentUseCase : Query<IGetAttachmentUseCase.Input, IGetAttachmentUseCase.Output?> {
    data class Input(val id: String)
    data class Output(val attachment: Attachment)
}