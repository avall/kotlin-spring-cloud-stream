package com.avall.kotlin.ms.cousine.consumer.domain.usecase.order

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IOrderRepository

@Interactor
open class GetOrderUseCase(private val repository: IOrderRepository) :
    UseCase<GetOrderUseCase.InputValues, GetOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: String = input.id
        return repository.getById(id)
            .map { order -> OutputValues(order) }
            .orElseThrow { NotFoundException("Order %s not found",id ) }
    }

    data class InputValues(
        var id: String
    ) : UseCase.InputValues


    data class OutputValues(
        var order: Order?
    ) : UseCase.OutputValues
}