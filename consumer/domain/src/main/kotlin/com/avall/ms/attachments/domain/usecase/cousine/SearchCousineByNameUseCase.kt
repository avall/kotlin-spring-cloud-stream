package com.avall.ms.attachments.domain.usecase.cousine

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.Cousine
import com.avall.ms.attachments.domain.port.output.ICousineRepository


@Interactor
open class SearchCousineByNameUseCase(private val repository: ICousineRepository) :
    UseCase<SearchCousineByNameUseCase.InputValues, SearchCousineByNameUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(cousines = repository.searchByName(input.searchText))
    }

    data class InputValues(
        var searchText: String
    ): UseCase.InputValues


    data class OutputValues(
        var cousines: List<Cousine>
    ) : UseCase.OutputValues
}