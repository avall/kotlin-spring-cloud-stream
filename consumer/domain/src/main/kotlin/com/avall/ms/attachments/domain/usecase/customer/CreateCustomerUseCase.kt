package com.avall.ms.attachments.domain.usecase.customer

import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.arch.exception.EntityAlreadyExistsException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.model.Customer
import com.avall.ms.attachments.domain.port.output.ICustomerRepository


@Interactor
open class CreateCustomerUseCase(
    private val repository: ICustomerRepository
): UseCase<CreateCustomerUseCase.InputValues, CreateCustomerUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        if (repository.existsByEmail(input.email)) {
            throw EntityAlreadyExistsException("Email address already in use!")
        }
        val customer = Customer(
            name=input.name,
            email=input.email,
            address=input.address,
            password=input.password
        )
        return OutputValues(repository.persist(customer))
    }

    data class InputValues (
        var name: String,
        var email: String,
        var address: String,
        var password: String
    ): UseCase.InputValues

    data class OutputValues (
        var customer: Customer
    ): UseCase.OutputValues
}
