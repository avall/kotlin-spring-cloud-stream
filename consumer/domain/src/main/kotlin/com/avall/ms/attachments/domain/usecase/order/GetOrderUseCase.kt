package com.avall.ms.attachments.domain.usecase.order

import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Order
import com.avall.ms.attachments.domain.port.output.IOrderRepository

@Interactor
open class GetOrderUseCase(private val repository: IOrderRepository) :
    UseCase<GetOrderUseCase.InputValues, GetOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        return repository.getById(id)
            .map { order -> OutputValues(order) }
            .orElseThrow { NotFoundException("Order %s not found",id.number ) }
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues


    data class OutputValues(
        var order: Order?
    ) : UseCase.OutputValues
}