package com.avall.kotlin.ms.cousine.producer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.producer.CommandPayload
import com.avall.kotlin.ms.cousine.producer.domain.model.Attachment
import com.avall.kotlin.ms.cousine.producer.domain.port.output.ISendAttachmentPort
import com.avall.kotlin.ms.cousine.producer.infrastructure.mapper.AttachmentEntityMapper
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.concurrent.BlockingQueue


@Component
class SendAttachmentAdapter(
    private val blockingQueuePayload: BlockingQueue<Message<CommandPayload>>
): ISendAttachmentPort {

    override fun execute(attachments: List<Attachment>) {
        blockingQueuePayload.offer(
            createEvent(AttachmentEntityMapper.toPayload(attachments))
        )
    }

    private fun createEvent(payload: CommandPayload): Message<CommandPayload> {
        return MessageBuilder.withPayload(payload)
//            .setHeader(KafkaHeaders.MESSAGE_KEY, "")
            .setHeader("x-tenant", "xx")
            .build()
    }

}
