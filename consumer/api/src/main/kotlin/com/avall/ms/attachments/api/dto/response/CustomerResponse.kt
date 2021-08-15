package com.avall.ms.attachments.api.dto.response

data class CustomerResponse (
    var id: Long,
    var name: String,
    var email: String,
    var address: String
)