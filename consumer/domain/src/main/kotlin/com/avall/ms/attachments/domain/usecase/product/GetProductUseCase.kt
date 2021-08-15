package com.avall.ms.attachments.domain.usecase.product

import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.port.output.IProductRepository


@Interactor
class GetProductUseCase(private val repository: IProductRepository) :
    UseCase<GetProductUseCase.InputValues, GetProductUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        return repository.getById(id)
            .map { product -> OutputValues(product)}
            .orElseThrow { NotFoundException("Product %s not found",id.number) }
    }

    data class InputValues (
        var id: Identity
    ): UseCase.InputValues

    data class OutputValues (
        var product: Product?
    ): UseCase.OutputValues
}