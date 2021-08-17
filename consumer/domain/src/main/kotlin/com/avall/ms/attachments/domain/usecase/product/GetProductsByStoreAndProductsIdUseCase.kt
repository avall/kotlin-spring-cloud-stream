package com.ferraobox.qamyapp.application.core.usecases.product

import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.arch.exception.NotFoundException
import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.port.output.IProductRepository
import java.util.stream.Collectors


@Interactor
class GetProductsByStoreAndProductsIdUseCase(private val repository: IProductRepository) :
    UseCase<GetProductsByStoreAndProductsIdUseCase.InputValues, GetProductsByStoreAndProductsIdUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val distinctProductsId: List<String> = input.productsId
        val foundProducts: List<Product> =
            repository.searchProductsByStoreAndProductsId(input.storeId, distinctProductsId)
        throwIfAnyProductIsNotFound(distinctProductsId, foundProducts)
        return OutputValues(foundProducts)
    }

    private fun throwIfAnyProductIsNotFound(
        distinctProductsId: List<String>,
        foundProducts: List<Product>
    ) {
        if (distinctProductsId.size != foundProducts.size) {
            val message = createErrorMessage(distinctProductsId, foundProducts)
            throw NotFoundException("404", message)
        }
    }

    private fun createErrorMessage(distinctProductsId: List<String>, foundProducts: List<Product>): String {
        val missingProductsId = getMissingProductsId(distinctProductsId, foundProducts)
        return String.format("Product(s) %s not found", java.lang.String.join(", ", missingProductsId))
    }

    private fun getMissingProductsId(distinctProductsId: List<String>, foundProducts: List<Product>): List<String> {
        val distinctProductsIdSet = createDistinctProductsIdSet(distinctProductsId)
        val foundProductsId = createFoundProductsIdSet(foundProducts)
        distinctProductsIdSet.removeAll(foundProductsId)
        return distinctProductsIdSet
            .stream()
            .collect(Collectors.toList())
    }

    private fun createFoundProductsIdSet(foundProducts: List<Product>): Set<String> {
        return foundProducts.map { product -> product.id!! }.toSet<String>()
    }

    private fun createDistinctProductsIdSet(distinctProductsId: List<String>): MutableSet<String> {
        return distinctProductsId.toMutableSet<String>()
    }

    private fun distinctIds(identities: List<String>): List<String> {
        return identities.distinct().toList<String>()
    }

    data class InputValues(
        var storeId: String,
        var productsId: List<String>
    ) : UseCase.InputValues

    data class OutputValues(
        var products: List<Product>
    ) : UseCase.OutputValues
}