package com.avall.ms.attachments.domain.usecase.order

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.Customer
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Order
import com.ferraobox.qamyapp.application.core.usecases.order.GetOrderUseCase


@Interactor
open class GetCustomerOrderUseCase(private val getOrderUseCase: GetOrderUseCase) :
    UseCase<GetCustomerOrderUseCase.InputValues, GetCustomerOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val order: Order? = getOrderUseCase
            .execute(input.let { GetOrderUseCase.InputValues(id= it.id) }).order
        return OutputValues(order!!.customer)
    }

    data class InputValues (
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues (
        var customer: Customer?
    ): UseCase.OutputValues


}