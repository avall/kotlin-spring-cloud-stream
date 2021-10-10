package com.avall.kotlin.ms.cousine.producer.api.dto.response

data class AuthenticationResponse (
    var success: Boolean = true,
    var token: String?
)