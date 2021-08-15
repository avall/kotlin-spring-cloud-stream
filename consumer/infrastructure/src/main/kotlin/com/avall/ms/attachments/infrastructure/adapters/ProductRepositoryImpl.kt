package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.port.output.IProductRepository
import com.avall.ms.attachments.infrastructure.mapper.ProductDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.repository.DbProductRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList

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

    override fun getById(id: Identity): Optional<Product> {
        return repository
            .findById(id.number)
            .map { p -> p?.mapToDomain() }
    }

    override fun searchByNameOrDescription(searchText: String): List<Product> {
        val products = repository.findByNameContainingOrDescriptionContainingAllIgnoreCase(searchText, searchText)
        return products.stream()
            .map { p -> p?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

    override fun searchProductsByStoreAndProductsId(storeId: Identity, productsId: List<Identity>): List<Product> {
        val products = repository.findByStoreIdAndIdIsIn(storeId.number, createListOfLong(productsId))
        return products.stream()
            .map { p -> p?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

    private fun createListOfLong(productsId: List<Identity>): List<Long> {
        return productsId
            .stream()
            .map { it.number }.toList<Long>()
    }
}
