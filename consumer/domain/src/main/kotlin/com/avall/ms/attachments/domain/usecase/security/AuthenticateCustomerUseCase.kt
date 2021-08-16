package com.avall.ms.attachments.domain.usecase.security

import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.arch.usecase.UseCase
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@Interactor
open class AuthenticateCustomerUseCase(
    private val authenticationManager: AuthenticationManager,
    private val jwtProvider: IJwtProvider
) : UseCase<AuthenticateCustomerUseCase.InputValues, AuthenticateCustomerUseCase.OutputValues> {

   override fun execute(input: InputValues): OutputValues {
        val authentication = authenticationManager.authenticate(input.authenticationToken)
        return OutputValues(jwtProvider.generateToken(authentication))
    }

    data class InputValues(
        var authenticationToken: UsernamePasswordAuthenticationToken
    ) : UseCase.InputValues

    data class OutputValues(
        var jwtToken: String
    ) : UseCase.OutputValues
}
