package com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CousineResponse
import com.avall.kotlin.ms.cousine.consumer.domain.model.Cousine
import org.springframework.stereotype.Component

@Component
object CousineDomainDtoMapper {

    fun Cousine.mapToDto(): CousineResponse {
        return CousineResponse(
            id = this.id!!,
            name = this.name
        )
    }

    fun List<Cousine>.mapToDto(): List<CousineResponse> {
        val cousineListResponse = ArrayList<CousineResponse>()
        forEach { cousine -> cousineListResponse.add(cousine.mapToDto()) }
        return cousineListResponse
    }

    fun CousineResponse.mapToDomain(): Cousine {
        return Cousine(
            id = this.id,
            name = this.name
        )
    }

    fun List<CousineResponse>.mapToDomain(): List<Cousine> {
        val cousineList = ArrayList<Cousine>()
        forEach { cousine -> cousineList.add(cousine.mapToDomain()) }
        return cousineList
    }


}