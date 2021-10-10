package com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.consumer.domain.model.AuthenticationUserPassword
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.AuthenticationPort
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.AuthenticationUserPasswordMapper.mapToAuthentication
import com.avall.kotlin.ms.cousine.consumer.infrastructure.security.IJwtProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component

@Component
class AuthenticationAdapter(
    private val authenticationManager: AuthenticationManager,
    private val jwtProvider: IJwtProvider
    ):AuthenticationPort {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: AuthenticationUserPassword): Object? {
        return authenticationManager.authenticate(authentication.mapToAuthentication()) as Object;
    }

    override fun generateToken(authentication: Object?): String {
        return jwtProvider.generateToken(authentication as Authentication);
    }

}