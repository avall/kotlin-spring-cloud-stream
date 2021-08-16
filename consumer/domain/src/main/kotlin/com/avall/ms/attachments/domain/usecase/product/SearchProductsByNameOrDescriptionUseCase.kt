package com.avall.ms.attachments.domain.usecase.product

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.port.output.IProductRepository

@Interactor
class SearchProductsByNameOrDescriptionUseCase(private val repository: IProductRepository) :
    UseCase<SearchProductsByNameOrDescriptionUseCase.InputValues, SearchProductsByNameOrDescriptionUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.searchByNameOrDescription(input.searchText))
    }

    class InputValues(
        var searchText: String
    ) : UseCase.InputValues

    data class OutputValues(
        var products: List<Product>
    ) : UseCase.OutputValues
}