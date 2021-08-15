package com.avall.ms.attachments.api.dto.request

data class SignUpRequest (
    var name: String,
    var email: String,
    var address: String,
    var password: String
)