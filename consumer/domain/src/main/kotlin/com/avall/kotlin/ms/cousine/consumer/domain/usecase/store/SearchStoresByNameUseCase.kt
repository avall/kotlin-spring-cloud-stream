package com.avall.kotlin.ms.cousine.consumer.domain.usecase.store

import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.model.Store
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IStoreRepository


@Interactor
class SearchStoresByNameUseCase(private val repository: IStoreRepository) :
    UseCase<SearchStoresByNameUseCase.InputValues, SearchStoresByNameUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.searchByName(input.searchText))
    }

    data class InputValues(
        var searchText: String
    ): UseCase.InputValues

    data class OutputValues (
        var stores: List<Store>
    ): UseCase.OutputValues
}