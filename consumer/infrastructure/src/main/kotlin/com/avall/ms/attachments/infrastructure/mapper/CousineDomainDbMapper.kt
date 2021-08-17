package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.Cousine
import com.avall.ms.attachments.infrastructure.database.CousineDb

object CousineDomainDbMapper {

    fun Cousine.mapToDb(): CousineDb {
        return CousineDb(id = this.id, name = this.name)
    }

    fun List<Cousine>.mapToDb(): List<CousineDb> {
        val cousineDbList = ArrayList<CousineDb>()
        forEach { cousine ->
            cousineDbList.add(cousine.mapToDb())
        }
        return cousineDbList
    }

    fun CousineDb.mapToDomain(): Cousine {
        return Cousine(id = this.id, name = this.name)
    }

    fun List<CousineDb>.mapToDomain(): List<Cousine> {
        val cousineList = ArrayList<Cousine>()
        forEach { cousineDB ->
            cousineList.add(cousineDB.mapToDomain())
        }
        return cousineList
    }
}