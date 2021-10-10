package com.avall.kotlin.ms.cousine.consumer.application.controller


import com.avall.kotlin.ms.cousine.consumer.api.StoreResource
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ProductResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.StoreResponse
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.ProductDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.StoreDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCaseExecutor
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.store.GetAllStoresUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.store.GetProductsByStoreUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.store.GetStoreUseCase
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.store.SearchStoresByNameUseCase
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture

@Component
class StoreController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllStoresUseCase: GetAllStoresUseCase,
    private val searchStoresByNameUseCase: SearchStoresByNameUseCase,
    private val getStoreUseCase: GetStoreUseCase,
    private val getProductsByStoreUseCase: GetProductsByStoreUseCase,
) : StoreResource {

    override fun all(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllStoresUseCase,
            GetAllStoresUseCase.InputValues()
        ) { outputValues -> outputValues.stores.mapToDto() }
    }

    override fun getAllStoresByNameMatching(@PathVariable text: String): CompletableFuture<List<StoreResponse?>?> {
        return useCaseExecutor.execute(
            searchStoresByNameUseCase,
            SearchStoresByNameUseCase.InputValues(searchText=text)
        ) { outputValues -> outputValues.stores.mapToDto() }
    }

    override fun getStoreByString(@PathVariable id: String): CompletableFuture<StoreResponse?> {
        return useCaseExecutor.execute(
            getStoreUseCase,
            GetStoreUseCase.InputValues(id= id)
        ) { outputValues -> outputValues.store!!.mapToDto() }
    }

    override fun getProductsBy(@PathVariable id: String): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            getProductsByStoreUseCase,
            GetProductsByStoreUseCase.InputValues(id=id)
        ) { outputValues -> outputValues.products.mapToDto() }
    }
}
