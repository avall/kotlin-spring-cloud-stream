package com.avall.kotlin.ms.cousine.consumer.domain.usecase.store

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Store
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IStoreRepository


@Interactor
class GetStoreUseCase(private val repository: IStoreRepository) :
    UseCase<GetStoreUseCase.InputValues, GetStoreUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: String = input.id
        return repository.getById(id)
            .map { store -> OutputValues(store) }
            .orElseThrow { NotFoundException("Store %s not found", id) }
    }

    data class InputValues(
        var id: String
    ) : UseCase.InputValues

    data class OutputValues(
        var store: Store?
    ) : UseCase.OutputValues

}
