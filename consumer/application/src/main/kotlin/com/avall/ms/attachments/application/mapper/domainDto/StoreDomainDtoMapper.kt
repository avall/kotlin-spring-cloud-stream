package com.avall.ms.attachments.application.mapper.domainDto

import com.avall.ms.attachments.api.dto.response.StoreResponse
import com.avall.ms.attachments.application.mapper.domainDto.CousineDomainDtoMapper.mapToDomain
import com.avall.ms.attachments.application.mapper.domainDto.CousineDomainDtoMapper.mapToDto
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Store
import org.springframework.stereotype.Component

@Component
object StoreDomainDtoMapper {

    fun Store.mapToDto(): StoreResponse {
        return StoreResponse(
            id = this.id.number,
            name = this.name,
            address = this.address,
            cousine = this.cousine.mapToDto()
        )
    }

    fun List<Store>.mapToDto(): List<StoreResponse> {
        val storeListResponse = ArrayList<StoreResponse>()
        forEach { store -> storeListResponse.add(store.mapToDto()) }
        return storeListResponse
    }


    fun StoreResponse.mapToDomain(): Store {
        return Store(
            id = Identity(this.id),
            name = this.name,
            address = this.address,
            cousine = this.cousine.mapToDomain()
        )
    }

    fun List<StoreResponse>.mapToDomain(): List<Store> {
        val storeList = ArrayList<Store>()
        forEach { store -> storeList.add(store.mapToDomain()) }
        return storeList
    }
}
