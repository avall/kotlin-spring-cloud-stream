package com.avall.kotlin.ms.cousine.consumer.domain.usecase.product

import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.model.Product
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IProductRepository


@Interactor
class GetAllProductsUseCase(private val repository: IProductRepository) :
    UseCase<GetAllProductsUseCase.InputValues, GetAllProductsUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.all())
    }

    class InputValues : UseCase.InputValues

    data class OutputValues(
        var products: List<Product>
    ) : UseCase.OutputValues

}