package com.avall.kotlin.ms.cousine.producer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.output.SendAttachmentPort
import com.avall.kotlin.ms.cousine.producer.infrastructure.mapper.AttachmentEntityMapper
import org.springframework.stereotype.Component
import java.util.concurrent.BlockingQueue


@Component
class SendAttachmentAdapter(
    private val attachmentMapper: AttachmentEntityMapper? = null,
    private val blockingQueuePayload: BlockingQueue<CommandPayload>
):SendAttachmentPort {

    fun execute(attachments: List<Attachment>) {
        publisherService.send(
            CommandCreateAttachments.newBuilder().setDocuments(
                claimMapper.toCommandAttachment(attachments)
            ).build(), Bindings.EXECUTE_CREATE_CRM_DOCUMENTS_COMMAND.toString() + "-out-0"
        )
    }
}
