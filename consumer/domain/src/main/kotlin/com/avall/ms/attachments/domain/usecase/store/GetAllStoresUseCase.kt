package com.avall.ms.attachments.domain.usecase.store

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.Store
import com.avall.ms.attachments.domain.port.output.IStoreRepository


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