package com.avall.ms.attachments.application.mapper

import com.avall.ms.attachments.CommandPayload
import com.avall.ms.attachments.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.ms.attachments.api.dto.response.CreateAttachmentResponse
import com.avall.ms.attachments.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.ms.attachments.api.dto.response.GetAttachmentResponse
import com.avall.ms.attachments.domain.model.Attachment
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentsUseCase
import java.util.stream.Collectors

object AttachmentsMapper {
    fun buildGetAttachmentQuery(id: String): IGetAttachmentUseCase.Input {
        return IGetAttachmentUseCase.Input(id)
    }
    fun buildGetAttachmentsQuery(parentId: String): IGetAttachmentsUseCase.Input {
        return IGetAttachmentsUseCase.Input(parentId)
    }
}

fun CreateAttachmentWrapperRequest.toCreateUseCaseInput(): ICreateAttachmentUseCase.Input {
    return ICreateAttachmentUseCase.Input(
            documents.stream()
                .map {
                    Attachment(
                        id                  = null,
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
        contentType         = contentType,
        description         = description,
        isPrivate           = isPrivate,
        url                 = url
    )
}

fun Attachment.toCreateAttachmentResponse(): CreateAttachmentResponse {
    return CreateAttachmentResponse(
        id                  = id!!,
        contentType         = contentType,
        description         = description,
        isPrivate           = isPrivate,
        url                 = url
    )
}
