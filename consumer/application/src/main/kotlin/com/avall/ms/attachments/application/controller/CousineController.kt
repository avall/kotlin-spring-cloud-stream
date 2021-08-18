package com.avall.ms.attachments.application.controller

import com.avall.ms.attachments.api.CousineResource
import com.avall.ms.attachments.api.dto.response.CousineResponse
import com.avall.ms.attachments.api.dto.response.StoreResponse
import com.avall.ms.attachments.application.mapper.domainDto.CousineDomainDtoMapper.mapToDto
import com.avall.ms.attachments.application.mapper.domainDto.StoreDomainDtoMapper.mapToDto
import com.avall.ms.attachments.arch.usecase.UseCaseExecutor
import com.avall.ms.attachments.domain.usecase.cousine.GetAllCousinesUseCase
import com.avall.ms.attachments.domain.usecase.cousine.GetStoresByCousineUseCase
import com.avall.ms.attachments.domain.usecase.cousine.SearchCousineByNameUseCase
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture

@Component
class CousineController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllCousinesUseCase: GetAllCousinesUseCase,
    private val getStoresByCousineUseCase: GetStoresByCousineUseCase,
    private val searchCousineByNameUseCase: SearchCousineByNameUseCase,
) : CousineResource {

    override fun getStoresByCousineId(@PathVariable id: String): CompletableFuture<List<StoreResponse?>?> {
        return useCaseExecutor.execute(
            getStoresByCousineUseCase,
            GetStoresByCousineUseCase.InputValues(id = id)
        ) { (outputValues) -> outputValues.mapToDto() }
    }

    override fun allCousines(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllCousinesUseCase,
            GetAllCousinesUseCase.InputValues()
        ) { outputValues -> outputValues.cousines.mapToDto() }
    }

    override fun getAllCousinesByNameMatching(@PathVariable text: String): CompletableFuture<List<CousineResponse?>?> {
        return useCaseExecutor.execute(
            searchCousineByNameUseCase,
            SearchCousineByNameUseCase.InputValues(searchText = text)
        ) { outputValues -> outputValues.cousines.mapToDto() }
    }
}
