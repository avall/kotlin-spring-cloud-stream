package com.avall.ms.attachments.api.dto.response

data class AuthenticationResponse (
    var success: Boolean = true,
    var token: String?
)