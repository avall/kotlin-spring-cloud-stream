package com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ProductResponse
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.StoreDomainDtoMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.StoreDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.domain.model.Product
import org.springframework.stereotype.Component

@Component
object ProductDomainDtoMapper {

    fun Product.mapToDto(): ProductResponse {
        return ProductResponse(
            id = this.id!!,
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDto()
        )
    }

    fun List<Product>.mapToDto(): List<ProductResponse> {
        val productListResponse = ArrayList<ProductResponse>()
        forEach { product -> productListResponse.add(product.mapToDto()) }
        return productListResponse
    }


    fun ProductResponse.mapToDomain(): Product {
        return Product(
            id = this.id,
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDomain()
        )
    }

    fun List<ProductResponse>.mapToDomain(): List<Product> {
        val productList = ArrayList<Product>()
        forEach { product -> productList.add(product.mapToDomain()) }
        return productList
    }
}