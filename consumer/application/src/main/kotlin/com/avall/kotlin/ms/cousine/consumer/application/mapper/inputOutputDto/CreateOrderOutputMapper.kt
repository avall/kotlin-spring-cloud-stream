package com.avall.kotlin.ms.cousine.consumer.application.mapper.inputOutputDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ApiResponse
import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import org.springframework.stereotype.Component

@Component
object CreateOrderOutputMapper {
    fun Order.map(): ApiResponse {
        return ApiResponse(success = true, message = "order created successfully" )
    }
}
