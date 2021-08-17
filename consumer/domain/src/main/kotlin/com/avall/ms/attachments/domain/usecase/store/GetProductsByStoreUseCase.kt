package com.avall.ms.attachments.domain.usecase.store

import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.port.output.IStoreRepository


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