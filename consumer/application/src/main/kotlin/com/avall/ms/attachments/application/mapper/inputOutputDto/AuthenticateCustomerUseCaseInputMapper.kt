package com.avall.ms.attachments.application.mapper.inputOutputDto

import com.avall.ms.attachments.api.dto.request.SignInRequest
import com.avall.ms.attachments.domain.usecase.security.AuthenticateCustomerUseCase
import org.springframework.stereotype.Component

@Component
object AuthenticateCustomerUseCaseInputMapper {
    fun SignInRequest.map(): AuthenticateCustomerUseCase.InputValues {
        return AuthenticateCustomerUseCase.InputValues(this.email,this.password)
    }
}
