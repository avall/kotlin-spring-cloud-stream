package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Attachment

interface IGetAttachmentsPort {
    fun findByParentId(parentId:String): List<Attachment>
}