package com.avall.kotlin.ms.cousine.consumer.application.mapper.inputOutputDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.request.SignUpRequest
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.customer.CreateCustomerUseCase
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder


object CreateCustomerInputMapper {

    private var encoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    fun SignUpRequest.map(): CreateCustomerUseCase.InputValues {
        return CreateCustomerUseCase.InputValues(
            password = encoder.encode(this.password),
            name = this.name,
            email = this.email,
            address = this.address
        )
    }
}
