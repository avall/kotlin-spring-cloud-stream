package com.avall.kotlin.ms.cousine.consumer.domain.usecase.store

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.arch.exception.NotFoundException
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.domain.model.Product
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IStoreRepository


@Interactor
class GetProductsByStoreUseCase(private val repository: IStoreRepository) :
    UseCase<GetProductsByStoreUseCase.InputValues, GetProductsByStoreUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: String = input.id
        val products: List<Product> = repository.getProductsById(id)
        if (products.isEmpty()) {
            throw NotFoundException("No store found by identity: " , id)
        }
        return OutputValues(products)
    }

    data class InputValues (
        var id: String
    ): UseCase.InputValues

    data class OutputValues (
        var products: List<Product>
    ): UseCase.OutputValues
}