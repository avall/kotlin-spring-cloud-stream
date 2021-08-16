package com.avall.ms.attachments.domain.usecase.store

import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Store
import com.avall.ms.attachments.domain.port.output.IStoreRepository


@Interactor
class GetStoreUseCase(private val repository: IStoreRepository) :
    UseCase<GetStoreUseCase.InputValues, GetStoreUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        return repository.getById(id)
            .map { store -> OutputValues(store) }
            .orElseThrow { NotFoundException("Store %s not found", id.number) }
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues(
        var store: Store?
    ) : UseCase.OutputValues

}
