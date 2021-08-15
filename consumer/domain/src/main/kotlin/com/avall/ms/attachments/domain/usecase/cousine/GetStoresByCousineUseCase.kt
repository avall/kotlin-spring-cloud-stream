package com.avall.ms.attachments.domain.usecase.cousine

import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Store
import com.avall.ms.attachments.domain.port.output.IStoreRepository


@Interactor
open class GetStoresByCousineUseCase(private val repository: IStoreRepository) :
    UseCase<GetStoresByCousineUseCase.InputValues, GetStoresByCousineUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        val stores: List<Store> = repository.getStoresById(id)
        if (stores.isEmpty()) {
            throw NotFoundException("Cousine %s not found",id.number)
        }
        return OutputValues(stores)
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues(
        var stores: List<Store>
    ) : UseCase.OutputValues
}
