package com.avall.ms.attachments.application.mapper.domainDto

import com.avall.ms.attachments.api.dto.response.CousineResponse
import com.avall.ms.attachments.domain.model.Cousine
import com.avall.ms.attachments.domain.model.Identity
import org.springframework.stereotype.Component

@Component
object CousineDomainDtoMapper {

    fun Cousine.mapToDto(): CousineResponse {
        return CousineResponse(
            id = this.id.number,
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
            id = Identity(this.id),
            name = this.name
        )
    }

    fun List<CousineResponse>.mapToDomain(): List<Cousine> {
        val cousineList = ArrayList<Cousine>()
        forEach { cousine -> cousineList.add(cousine.mapToDomain()) }
        return cousineList
    }


}