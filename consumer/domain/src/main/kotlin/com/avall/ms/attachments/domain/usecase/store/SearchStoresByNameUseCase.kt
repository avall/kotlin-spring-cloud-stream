package com.avall.ms.attachments.domain.usecase.store

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.Store
import com.avall.ms.attachments.domain.port.output.IStoreRepository


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