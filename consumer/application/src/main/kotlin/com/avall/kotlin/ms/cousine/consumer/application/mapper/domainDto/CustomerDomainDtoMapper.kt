package com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CustomerResponse
import com.avall.kotlin.ms.cousine.consumer.domain.model.Customer
import org.springframework.stereotype.Component

@Component
object CustomerDomainDtoMapper {

    fun Customer.mapToDto(): CustomerResponse {
        return CustomerResponse(
            id = this.id!!,
            name = this.name,
            email = this.email, address = this.address
        )
    }

    fun List<Customer>.mapToDto(): List<CustomerResponse> {
        val customerListResponse = ArrayList<CustomerResponse>()
        forEach { customer -> customerListResponse.add(customer.mapToDto()) }
        return customerListResponse
    }

    fun CustomerResponse.mapToDomain(): Customer {
        return Customer(
            id = this.id,
            name = this.name, email = this.email, address = this.address, password = ""
        )
    }

    fun List<CustomerResponse>.mapToDomain(): List<Customer> {
        val customerList = ArrayList<Customer>()
        forEach { customer -> customerList.add(customer.mapToDomain()) }
        return customerList
    }

}
