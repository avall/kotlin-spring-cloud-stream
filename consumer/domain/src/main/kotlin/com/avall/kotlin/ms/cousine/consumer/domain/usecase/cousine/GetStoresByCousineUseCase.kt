package com.avall.kotlin.ms.cousine.consumer.domain.usecase.cousine

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Store
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IStoreRepository


@Interactor
open class GetStoresByCousineUseCase(private val repository: IStoreRepository) :
    UseCase<GetStoresByCousineUseCase.InputValues, GetStoresByCousineUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: String = input.id
        val stores: List<Store> = repository.getStoresById(id)
        if (stores.isEmpty()) {
            throw NotFoundException("Cousine %s not found",id)
        }
        return OutputValues(stores)
    }

    data class InputValues(
        var id: String
    ) : UseCase.InputValues

    data class OutputValues(
        var stores: List<Store>
    ) : UseCase.OutputValues
}
