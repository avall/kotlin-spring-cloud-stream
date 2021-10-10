package com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper

import com.avall.kotlin.ms.cousine.consumer.domain.model.Store
import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.StoreDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.CousineDomainDbMapper.mapToDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.CousineDomainDbMapper.mapToDomain


object StoreDomainDbMapper {

    fun Store.mapToDb(): StoreDb {
        return StoreDb(
            id = this.id,
            name = this.name,
            address = this.address,
            cousine = this.cousine.mapToDb()
        )
    }

    fun List<Store>.mapToDb(): MutableSet<StoreDb> {
        val storeDbList: MutableSet<StoreDb> = HashSet()
        forEach { store ->
            storeDbList.add(store.mapToDb())
        }
        return storeDbList
    }

    fun StoreDb.mapToDomain(): Store {
        return Store(
            id = this.id,
            name = this.name,
            address = this.address,
            cousine = this.cousine.mapToDomain()
        )
    }

    fun MutableSet<StoreDb>.mapToDomain(): List<Store> {
        val storeList = ArrayList<Store>()
        forEach { store ->
            storeList.add(store.mapToDomain())
        }
        return storeList
    }
}