package com.avall.kotlin.ms.cousine.consumer.application.mapper.inputOutputDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.request.SignInRequest
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.security.AuthenticateCustomerUseCase
import org.springframework.stereotype.Component

@Component
object AuthenticateCustomerUseCaseInputMapper {
    fun SignInRequest.map(): AuthenticateCustomerUseCase.InputValues {
        return AuthenticateCustomerUseCase.InputValues(this.email,this.password)
    }
}
