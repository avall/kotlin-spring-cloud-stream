package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.AuthenticationUserPassword
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

object AuthenticationUserPasswordMapper {
    fun AuthenticationUserPassword.mapToAuthentication(): Authentication {
        return UsernamePasswordAuthenticationToken(this.principal ,this.credentials)
    }
}