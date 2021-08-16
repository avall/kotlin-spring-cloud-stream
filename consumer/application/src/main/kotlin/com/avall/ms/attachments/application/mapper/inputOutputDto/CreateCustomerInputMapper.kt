package com.avall.ms.attachments.application.mapper.inputOutputDto

import com.avall.ms.attachments.api.dto.request.SignUpRequest
import com.avall.ms.attachments.domain.usecase.customer.CreateCustomerUseCase
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
