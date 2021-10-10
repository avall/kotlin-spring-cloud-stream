package com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.consumer.domain.model.Product
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IProductRepository
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.ProductDomainDbMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.infrastructure.repository.DbProductRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors

@Repository
class ProductRepositoryImpl(
    private val repository: DbProductRepository,
) : IProductRepository {

    override fun all(): List<Product> {
        return repository
            .findAll()
            .stream()
            .map { p -> p?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

    override fun getById(id: String): Optional<Product> {
        return repository
            .findById(id)
            .map { p -> p?.mapToDomain() }
    }

    override fun searchByNameOrDescription(searchText: String): List<Product> {
        val products = repository.findByNameContainingOrDescriptionContainingAllIgnoreCase(searchText, searchText)
        return products.stream()
            .map { p -> p?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

    override fun searchProductsByStoreAndProductsId(storeId: String, productsId: List<String>): List<Product> {
        val products = repository.findByStoreIdAndIdIsIn(storeId, productsId)
        return products.stream()
            .map { p -> p?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

}
