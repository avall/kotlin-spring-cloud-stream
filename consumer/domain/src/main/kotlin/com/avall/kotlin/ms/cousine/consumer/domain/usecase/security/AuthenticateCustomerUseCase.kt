package com.avall.kotlin.ms.cousine.consumer.domain.usecase.security

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.AuthenticationUserPassword
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.AuthenticationPort

@Interactor
open class AuthenticateCustomerUseCase(
    private val authenticationPort: AuthenticationPort
) : UseCase<AuthenticateCustomerUseCase.InputValues, AuthenticateCustomerUseCase.OutputValues> {

   override fun execute(input: InputValues): OutputValues {
        val authentication = authenticationPort.authenticate(
            AuthenticationUserPassword(
                principal = input.email,
                credentials = input.password
            ))
        return OutputValues(authenticationPort.generateToken(authentication))
    }

    data class InputValues(
        var email: String,
        var password: String
    ) : UseCase.InputValues

    data class OutputValues(
        var jwtToken: String
    ) : UseCase.OutputValues
}
