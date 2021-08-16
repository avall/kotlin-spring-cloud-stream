package com.avall.ms.attachments.infrastructure.security

import org.springframework.security.core.Authentication

interface IJwtProvider {
    fun generateToken(authentication: Authentication): String
    fun generateToken(customerId: Long?): String
    fun getUserIdFromToken(token: String?): Long
    fun validateToken(authToken: String?): Boolean
}