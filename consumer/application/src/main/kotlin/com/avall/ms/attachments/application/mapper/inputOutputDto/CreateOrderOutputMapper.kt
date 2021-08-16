package com.avall.ms.attachments.application.mapper.inputOutputDto

import com.avall.ms.attachments.api.dto.response.ApiResponse
import com.avall.ms.attachments.domain.model.Order
import org.springframework.stereotype.Component

@Component
object CreateOrderOutputMapper {
    fun Order.map(): ApiResponse {
        return ApiResponse(success = true, message = "order created successfully" )
    }
}
