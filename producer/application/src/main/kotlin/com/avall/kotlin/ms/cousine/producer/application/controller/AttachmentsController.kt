package com.avall.kotlin.ms.cousine.producer.application.controller

import com.avall.kotlin.ms.cousine.producer.api.AttachmentApi
import com.avall.kotlin.ms.cousine.producer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.producer.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.producer.application.mapper.toCreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.producer.application.mapper.toCreateUseCaseInput
import com.avall.kotlin.ms.cousine.producer.domain.port.input.ISendAttachmentUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class AttachmentsController(
    private val sendAttachmentUseCase: ISendAttachmentUseCase
) : AttachmentApi {

    /**
     * Insert AttachmentWrapper into CRM
     * @param req CreateAttachmentWrapperRequest
     */
    override fun createAttachment(req: CreateAttachmentWrapperRequest): CreateAttachmentWrapperResponse =
        sendAttachmentUseCase.execute(req.toCreateUseCaseInput())
            .toCreateAttachmentWrapperResponse()


}
