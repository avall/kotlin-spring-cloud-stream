package com.avall.kotlin.ms.cousine.consumer.api.dto.response

data class AuthenticationResponse (
    var success: Boolean = true,
    var token: String?
)