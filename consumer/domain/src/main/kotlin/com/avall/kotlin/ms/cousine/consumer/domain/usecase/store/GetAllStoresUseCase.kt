package com.avall.kotlin.ms.cousine.consumer.domain.usecase.store

import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.model.Store
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IStoreRepository


@Interactor
class GetAllStoresUseCase(private val repository: IStoreRepository) :
    UseCase<GetAllStoresUseCase.InputValues, GetAllStoresUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.all())
    }

    class InputValues : UseCase.InputValues

    data class OutputValues (
        var stores: List<Store>
    ): UseCase.OutputValues
}