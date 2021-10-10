package com.avall.kotlin.ms.cousine.consumer.domain.usecase.order

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Customer
import com.avall.kotlin.ms.cousine.consumer.domain.model.Order


@Interactor
open class GetCustomerOrderUseCase(private val getOrderUseCase: GetOrderUseCase) :
    UseCase<GetCustomerOrderUseCase.InputValues, GetCustomerOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val order: Order? = getOrderUseCase
            .execute(input.let { GetOrderUseCase.InputValues(id= it.id) }).order
        return OutputValues(order!!.customer)
    }

    data class InputValues (
        var id: String
    ) : UseCase.InputValues

    data class OutputValues (
        var customer: Customer?
    ): UseCase.OutputValues


}