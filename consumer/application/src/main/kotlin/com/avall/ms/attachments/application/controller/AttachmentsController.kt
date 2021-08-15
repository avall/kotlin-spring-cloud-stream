package com.avall.ms.attachments.application.controller

import com.avall.ms.attachments.api.AttachmentApi
import com.avall.ms.attachments.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.ms.attachments.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.ms.attachments.api.dto.response.GetAttachmentResponse
import com.avall.ms.attachments.application.exceptions.AttachmentNotFoundException
import com.avall.ms.attachments.application.mapper.AttachmentsMapper
import com.avall.ms.attachments.application.mapper.toCreateAttachmentWrapperResponse
import com.avall.ms.attachments.application.mapper.toCreateUseCaseInput
import com.avall.ms.attachments.application.mapper.toGetAttachmentResponse
import com.avall.ms.attachments.domain.port.input.ICreateAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentUseCase
import com.avall.ms.attachments.domain.port.input.IGetAttachmentsUseCase
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
     * Get AttachmentsWrappers by parentId.
     * @param parentId String
     * @return List<GetAttachmentsResponse>
     */
    override fun getAttachments(parentId:String): List<GetAttachmentResponse> =
        getAttachmentsUseCase.execute(AttachmentsMapper.buildGetAttachmentsQuery(parentId))
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
