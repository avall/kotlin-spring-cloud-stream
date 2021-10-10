package com.avall.kotlin.ms.cousine.consumer.application.controller


import com.avall.kotlin.ms.cousine.consumer.api.ProductResource
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ProductResponse
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.ProductDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCaseExecutor
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.product.GetAllProductsUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.product.GetProductUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.product.SearchProductsByNameOrDescriptionUseCase
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture

@Component
class ProductController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val searchProductsByNameOrDescriptionUseCase: SearchProductsByNameOrDescriptionUseCase,
) : ProductResource {

    override fun allProducts(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllProductsUseCase,
            GetAllProductsUseCase.InputValues()
        ) { outputValues -> outputValues.products.mapToDto() }
    }

    override fun getByString(@PathVariable id: String): CompletableFuture<ProductResponse?> {
        return useCaseExecutor.execute(
            getProductUseCase,
            GetProductUseCase.InputValues(id = id)
        ) { outputValues -> outputValues.product!!.mapToDto() }
    }

    override fun getByMatchingName(@PathVariable text: String): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            searchProductsByNameOrDescriptionUseCase,
            SearchProductsByNameOrDescriptionUseCase.InputValues(searchText = text)
        ) { outputValues -> outputValues.products.mapToDto() }
    }
}
