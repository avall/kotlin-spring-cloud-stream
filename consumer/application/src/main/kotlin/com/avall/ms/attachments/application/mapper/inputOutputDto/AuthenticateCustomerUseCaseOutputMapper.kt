package com.avall.ms.attachments.application.mapper.inputOutputDto

import com.avall.ms.attachments.api.dto.response.AuthenticationResponse
import com.avall.ms.attachments.domain.usecase.security.AuthenticateCustomerUseCase
import org.springframework.stereotype.Component

@Component
object AuthenticateCustomerUseCaseOutputMapper {
    fun AuthenticateCustomerUseCase.OutputValues.map(): AuthenticationResponse {
        return AuthenticationResponse(success = true, this.jwtToken)
    }
}