package com.avall.kotlin.ms.cousine.producer.application.controller

import com.avall.kotlin.ms.cousine.producer.api.AttachmentApi
import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.producer.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.producer.api.dto.response.GetAttachmentResponse
import com.avall.kotlin.ms.cousine.producer.application.exceptions.AttachmentNotFoundException
import com.avall.kotlin.ms.cousine.producer.application.mapper.AttachmentsMapper
import com.avall.kotlin.ms.cousine.producer.application.mapper.toCreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.producer.application.mapper.toCreateUseCaseInput
import com.avall.kotlin.ms.cousine.producer.application.mapper.toGetAttachmentResponse
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ICreateAttachmentUseCase
import com.avall.kotlin.ms.cousine.producer.domain.port.input.IGetAttachmentUseCase
import com.avall.kotlin.ms.cousine.producer.domain.port.input.IGetAttachmentsUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class AttachmentsController(
    private val createAttachmentUseCase: ICreateAttachmentUseCase
) : AttachmentApi {

    /**
     * Insert AttachmentWrapper into CRM
     * @param req CreateAttachmentWrapperRequest
     */
    override fun createAttachment(req: CreateAttachmentWrapperRequest): CreateAttachmentWrapperResponse =
        createAttachmentUseCase.execute(req.toCreateUseCaseInput())
            .toCreateAttachmentWrapperResponse()


}