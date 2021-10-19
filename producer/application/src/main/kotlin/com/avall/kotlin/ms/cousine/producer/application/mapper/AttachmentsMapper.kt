package com.avall.kotlin.ms.cousine.producer.application.mapper

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.producer.api.dto.response.CreateAttachmentResponse
import com.avall.kotlin.ms.cousine.producer.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import java.util.stream.Collectors

object AttachmentsMapper {
}

fun CreateAttachmentWrapperRequest.toCreateUseCaseInput(): ISendAttachmentUseCase.Input {
    return ISendAttachmentUseCase.Input(
            attachments = documents.stream()
                .map {
                    Attachment(
                        id                  = null,
                        objectId            = it.objectId!!,
                        objectName          = it.objectName!!,
                        description         = it.description,
                        contentType         = it.contentType!!,
                        isPrivate           = it.isPrivate,
                        url                 = it.url!!

                    )
                }.collect(Collectors.toList())
    )
}

fun CommandPayload.toCreateUseCaseInput(pTenant:String): ISendAttachmentUseCase.Input {
    return ISendAttachmentUseCase.Input(
        attachments = documents
            .stream()
            .map { a ->
                Attachment(
                    id                  = null,
                    objectId            = a.objectId,
                    objectName          = a.objectName,
                    description         = a.description,
                    contentType         = a.contentType,
                    isPrivate           = a.isPrivate,
                    url                 = a.url)
            }
            .collect(Collectors.toList())
        )
}


fun ISendAttachmentUseCase.Output.toCreateAttachmentWrapperResponse(): CreateAttachmentWrapperResponse {
    return CreateAttachmentWrapperResponse(attachments.map { a -> a.toCreateAttachmentResponse() })
}


fun Attachment.toCreateAttachmentResponse(): CreateAttachmentResponse {
    return CreateAttachmentResponse(
        id                  = id!!,
        objectId            = objectId,
        objectName          = objectName,
        contentType         = contentType,
        description         = description,
        isPrivate           = isPrivate,
        url                 = url
    )
}
