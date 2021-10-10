package com.avall.kotlin.ms.cousine.consumer.api.dto.request

data class SignUpRequest (
    var name: String,
    var email: String,
    var address: String,
    var password: String
)