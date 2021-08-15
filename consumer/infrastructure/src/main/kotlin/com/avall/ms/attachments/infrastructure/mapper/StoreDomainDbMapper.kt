package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.domain.model.Store
import com.avall.ms.attachments.infrastructure.database.StoreDb
import com.avall.ms.attachments.infrastructure.mapper.CousineDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.mapper.CousineDomainDbMapper.mapToDomain


object StoreDomainDbMapper {

    fun Store.mapToDb(): StoreDb {
        return StoreDb(
            id = this.id.number,
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
            id = Identity(this.id!!),
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