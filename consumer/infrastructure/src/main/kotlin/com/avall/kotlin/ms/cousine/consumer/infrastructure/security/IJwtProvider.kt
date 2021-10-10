package com.avall.kotlin.ms.cousine.consumer.infrastructure.security

import org.springframework.security.core.Authentication

interface IJwtProvider {
    fun generateToken(authentication: Authentication): String
    fun generateToken(customerId: String?): String
    fun getUserIdFromToken(token: String?): String
    fun validateToken(authToken: String?): Boolean
}