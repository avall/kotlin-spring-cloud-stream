package com.avall.kotlin.ms.cousine.consumer.domain.usecase.order

import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IOrderRepository

abstract class UpdateOrderUseCase(protected val repository: IOrderRepository) :
    UseCase<UpdateOrderUseCase.InputValues, UpdateOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: String = input.id
        return repository.getById(id).map { order -> updateStatus(order) }
            .map { order -> order?.let { persistAndReturn(it) } }
            .orElseThrow { NotFoundException("Order %s not found", id) }
    }

    protected abstract fun updateStatus(order: Order): Order

    private fun persistAndReturn(order: Order): OutputValues {
        return OutputValues(repository.persist(order))
    }

    data class InputValues(
        var id: String
    ) : UseCase.InputValues

    data class OutputValues(
        var order: Order?
    ) : UseCase.OutputValues
}