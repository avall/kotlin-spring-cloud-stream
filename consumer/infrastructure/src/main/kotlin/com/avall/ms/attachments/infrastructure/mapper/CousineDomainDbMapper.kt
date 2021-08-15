package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.Cousine
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.infrastructure.database.CousineDb
import java.util.*

object CousineDomainDbMapper {

    fun Cousine.mapToDb(): CousineDb {
        return CousineDb(id = this.id.number, name = this.name)
    }

    fun List<Cousine>.mapToDb(): List<CousineDb> {
        val cousineDbList = ArrayList<CousineDb>()
        forEach { cousine ->
            cousineDbList.add(cousine.mapToDb())
        }
        return cousineDbList
    }

    fun CousineDb.mapToDomain(): Cousine {
        return Cousine(id = Identity(this.id!!), name = this.name)
    }

    fun List<CousineDb>.mapToDomain(): List<Cousine> {
        val cousineList = ArrayList<Cousine>()
        forEach { cousineDB ->
            cousineList.add(cousineDB.mapToDomain())
        }
        return cousineList
    }
}