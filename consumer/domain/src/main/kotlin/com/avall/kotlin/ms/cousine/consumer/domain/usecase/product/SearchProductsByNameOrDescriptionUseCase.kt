package com.avall.kotlin.ms.cousine.consumer.domain.usecase.product

import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.model.Product
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IProductRepository

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