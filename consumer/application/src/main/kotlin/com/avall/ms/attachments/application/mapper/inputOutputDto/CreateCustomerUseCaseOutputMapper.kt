package com.avall.ms.attachments.application.mapper.inputOutputDto

import com.avall.ms.attachments.api.dto.response.ApiResponse
import com.avall.ms.attachments.domain.model.Customer
import org.springframework.stereotype.Component

@Component
object CreateCustomerUseCaseOutputMapper {
    fun Customer.map(): ApiResponse {
        return ApiResponse(success = true, message = "registered successfully" )
    }
}