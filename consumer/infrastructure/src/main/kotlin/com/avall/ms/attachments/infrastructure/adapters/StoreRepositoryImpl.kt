package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.domain.model.Store
import com.avall.ms.attachments.domain.port.output.IStoreRepository
import com.avall.ms.attachments.infrastructure.mapper.ProductDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.mapper.StoreDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.repository.DbStoreRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors


@Repository
open class StoreRepositoryImpl(
    private val repository: DbStoreRepository,
) : IStoreRepository {

    override fun all(): List<Store>{
        return repository
            .findAll()
            .parallelStream()
            .map { store ->store?.mapToDomain() }
            .collect(Collectors.toList<Store>())
    }

    override fun searchByName(searchText: String): List<Store> {
        return repository
            .findByNameContainingIgnoreCase(searchText)
            .parallelStream()
            .map { store -> store?.mapToDomain() }
            .collect(Collectors.toList<Store>())
    }

    override fun getById(id: String): Optional<Store> {
        return repository
            .findById(id)
            .map { store -> store?.mapToDomain() }
    }

    override fun getProductsById(id: String): List<Product> {
        return repository
            .findProductsById(id)
            .stream()
            .map { product -> product?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

    override fun getStoresById(id: String): List<Store> {
        return repository
            .findStoresById(id)
            .parallelStream()
            .map { store -> store?.mapToDomain() }
            .collect(Collectors.toList<Store>())
    }
}
