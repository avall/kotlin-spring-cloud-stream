package com.avall.ms.attachments.application.controller


import com.avall.ms.attachments.api.ProductResource
import com.avall.ms.attachments.api.dto.response.ProductResponse
import com.avall.ms.attachments.application.mapper.domainDto.ProductDomainDtoMapper.mapToDto
import com.avall.ms.attachments.arch.usecase.UseCaseExecutor
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.usecase.product.GetAllProductsUseCase
import com.avall.ms.attachments.domain.usecase.product.GetProductUseCase
import com.avall.ms.attachments.domain.usecase.product.SearchProductsByNameOrDescriptionUseCase
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

    override fun getByIdentity(@PathVariable id: Long): CompletableFuture<ProductResponse?> {
        return useCaseExecutor.execute(
            getProductUseCase,
            GetProductUseCase.InputValues(id = Identity(id))
        ) { outputValues -> outputValues.product!!.mapToDto() }
    }

    override fun getByMatchingName(@PathVariable text: String): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            searchProductsByNameOrDescriptionUseCase,
            SearchProductsByNameOrDescriptionUseCase.InputValues(searchText = text)
        ) { outputValues -> outputValues.products.mapToDto() }
    }
}
