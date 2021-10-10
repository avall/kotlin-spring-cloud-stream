package com.avall.kotlin.ms.cousine.consumer.application.mapper

import com.avall.kotlin.ms.cousine.consumer.CommandPayload
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CreateAttachmentResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.GetAttachmentResponse
import com.avall.kotlin.ms.cousine.consumer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.ICreateAttachmentUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentsUseCase
import java.util.stream.Collectors

object AttachmentsMapper {
    fun buildGetAttachmentQuery(id: String): IGetAttachmentUseCase.Input {
        return IGetAttachmentUseCase.Input(id)
    }
    fun buildGetAttachmentsQuery(objectId: String): IGetAttachmentsUseCase.Input {
        return IGetAttachmentsUseCase.Input(objectId)
    }
}

fun CreateAttachmentWrapperRequest.toCreateUseCaseInput(): ICreateAttachmentUseCase.Input {
    return ICreateAttachmentUseCase.Input(
            documents.stream()
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
                }.collect(Collectors.toList()))
}

fun CommandPayload.toCreateUseCaseInput(): ICreateAttachmentUseCase.Input {
    return ICreateAttachmentUseCase.Input(
        documents
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


fun IGetAttachmentsUseCase.Output.toGetAttachmentResponse(): List<GetAttachmentResponse> {
    return attachments.map { a -> a.toGetAttachmentResponse() }
}

fun ICreateAttachmentUseCase.Output.toCreateAttachmentWrapperResponse(): CreateAttachmentWrapperResponse {
    return CreateAttachmentWrapperResponse(attachments.map { a -> a.toCreateAttachmentResponse() })
}

fun Attachment.toGetAttachmentResponse(): GetAttachmentResponse {
    return GetAttachmentResponse(
        id                  = id!!,
        objectId            = objectId,
        objectName          = objectName,
        contentType         = contentType,
        description         = description,
        isPrivate           = isPrivate,
        url                 = url
    )
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
