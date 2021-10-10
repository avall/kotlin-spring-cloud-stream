package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.Cousine

interface ICousineRepository {
    fun all(): List<Cousine>
    fun searchByName(search: String): List<Cousine>
}
