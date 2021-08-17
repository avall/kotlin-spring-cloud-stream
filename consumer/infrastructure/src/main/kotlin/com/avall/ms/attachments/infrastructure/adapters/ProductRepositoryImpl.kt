package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.port.output.IProductRepository
import com.avall.ms.attachments.infrastructure.mapper.ProductDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.repository.DbProductRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors

@Repository
open class ProductRepositoryImpl(
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
