package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.AuthenticationUserPassword
import com.avall.ms.attachments.domain.port.output.AuthenticationPort
import com.avall.ms.attachments.infrastructure.mapper.AuthenticationUserPasswordMapper.mapToAuthentication
import com.avall.ms.attachments.infrastructure.security.IJwtProvider
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