package com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper

import com.avall.kotlin.ms.cousine.consumer.domain.model.AuthenticationUserPassword
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

object AuthenticationUserPasswordMapper {
    fun AuthenticationUserPassword.mapToAuthentication(): Authentication {
        return UsernamePasswordAuthenticationToken(this.principal ,this.credentials)
    }
}