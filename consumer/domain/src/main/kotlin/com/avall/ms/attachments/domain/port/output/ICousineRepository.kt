package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Cousine

interface ICousineRepository {
    fun all(): List<Cousine>
    fun searchByName(search: String): List<Cousine>
}
