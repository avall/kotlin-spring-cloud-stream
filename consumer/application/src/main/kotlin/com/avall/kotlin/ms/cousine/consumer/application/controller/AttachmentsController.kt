package com.avall.kotlin.ms.cousine.consumer.application.controller

import com.avall.kotlin.ms.cousine.consumer.api.AttachmentApi
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.GetAttachmentResponse
import com.avall.kotlin.ms.cousine.consumer.application.exceptions.AttachmentNotFoundException
import com.avall.kotlin.ms.cousine.consumer.application.mapper.AttachmentsMapper
import com.avall.kotlin.ms.cousine.consumer.application.mapper.toCreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.consumer.application.mapper.toCreateUseCaseInput
import com.avall.kotlin.ms.cousine.consumer.application.mapper.toGetAttachmentResponse
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.ICreateAttachmentUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.port.input.IGetAttachmentsUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class AttachmentsController(
    private val createAttachmentUseCase: ICreateAttachmentUseCase,
    private val getAttachmentUseCase: IGetAttachmentUseCase,
    private val getAttachmentsUseCase: IGetAttachmentsUseCase
) : AttachmentApi {

    /**
     * Insert AttachmentWrapper into CRM
     * @param req CreateAttachmentWrapperRequest
     */
    override fun createAttachment(req: CreateAttachmentWrapperRequest): CreateAttachmentWrapperResponse =
        createAttachmentUseCase.execute(req.toCreateUseCaseInput())
            .toCreateAttachmentWrapperResponse()

    /**
     * Get AttachmentsWrappers by objectId.
     * @param objectId String
     * @return List<GetAttachmentsResponse>
     */
    override fun getAttachments(objectId:String): List<GetAttachmentResponse> =
        getAttachmentsUseCase.execute(AttachmentsMapper.buildGetAttachmentsQuery(objectId))
            .toGetAttachmentResponse()

    /**
     * Get attachmentWrapper by Id;
     * @param id String
     * @return GetAttachmentResponse
     */
    override fun getAttachmentById(id: String): GetAttachmentResponse = try {
        (getAttachmentUseCase.execute(AttachmentsMapper.buildGetAttachmentQuery(id))?.attachment ?: throw AttachmentNotFoundException(id)).toGetAttachmentResponse()
    } catch (e: NoSuchElementException) { throw AttachmentNotFoundException(id) }

}
