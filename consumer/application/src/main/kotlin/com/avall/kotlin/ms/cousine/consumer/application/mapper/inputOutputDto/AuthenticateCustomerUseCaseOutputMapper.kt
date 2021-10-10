package com.avall.kotlin.ms.cousine.consumer.application.mapper.inputOutputDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.AuthenticationResponse
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.security.AuthenticateCustomerUseCase
import org.springframework.stereotype.Component

@Component
object AuthenticateCustomerUseCaseOutputMapper {
    fun AuthenticateCustomerUseCase.OutputValues.map(): AuthenticationResponse {
        return AuthenticationResponse(success = true, this.jwtToken)
    }
}