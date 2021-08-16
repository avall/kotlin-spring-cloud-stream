package com.ferraobox.qamyapp.application.presenter.rest.api.customer


import com.avall.ms.attachments.api.StoreResource
import com.avall.ms.attachments.api.dto.response.ProductResponse
import com.avall.ms.attachments.api.dto.response.StoreResponse
import com.avall.ms.attachments.application.mapper.domainDto.ProductDomainDtoMapper.mapToDto
import com.avall.ms.attachments.application.mapper.domainDto.StoreDomainDtoMapper.mapToDto
import com.avall.ms.attachments.arch.usecase.UseCaseExecutor
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.usecase.store.GetAllStoresUseCase
import com.avall.ms.attachments.domain.usecase.store.GetProductsByStoreUseCase
import com.avall.ms.attachments.domain.usecase.store.GetStoreUseCase
import com.avall.ms.attachments.domain.usecase.store.SearchStoresByNameUseCase
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

    override fun getStoreByIdentity(@PathVariable id: Long): CompletableFuture<StoreResponse?> {
        return useCaseExecutor.execute(
            getStoreUseCase,
            GetStoreUseCase.InputValues(id= Identity(id))
        ) { outputValues -> outputValues.store!!.mapToDto() }
    }

    override fun getProductsBy(@PathVariable id: Long): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            getProductsByStoreUseCase,
            GetProductsByStoreUseCase.InputValues(id=Identity(id))
        ) { outputValues -> outputValues.products.mapToDto() }
    }
}
