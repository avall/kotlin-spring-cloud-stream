package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.Product
import com.avall.ms.attachments.infrastructure.database.ProductDb
import com.avall.ms.attachments.infrastructure.mapper.StoreDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.mapper.StoreDomainDbMapper.mapToDomain

object ProductDomainDbMapper {

    fun Product.mapToDb(): ProductDb {
        return ProductDb(
            id = this.id,
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDb()
        )
    }

    fun List<Product>.mapToDb(): MutableSet<ProductDb> {
        val productListDb: MutableSet<ProductDb> = HashSet()
        forEach { product -> productListDb.add(product.mapToDb()) }
        return productListDb
    }

    fun ProductDb.mapToDomain(): Product {
        return Product(
            id = this.id,
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDomain()
        )
    }

    fun MutableSet<ProductDb>.mapToDomain(): List<Product> {
        val productList = ArrayList<Product>()
        forEach { productDb -> productList.add(productDb.mapToDomain()) }
        return productList
    }
}