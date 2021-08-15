package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Cousine
import com.avall.ms.attachments.domain.port.output.ICousineRepository
import com.avall.ms.attachments.infrastructure.mapper.CousineDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.repository.DbCousineRepository
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
open class CousineRepositoryImpl(
    private val repository: DbCousineRepository,
) : ICousineRepository {

    override fun all(): List<Cousine> {
        return repository
            .findAll()
            .parallelStream()
            .map { c -> c?.mapToDomain() }
            .collect(Collectors.toList<Cousine>())
    }

    override fun searchByName(search: String): List<Cousine> {
        return repository
            .findByNameContainingIgnoreCase(search)
            .parallelStream()
            .map { c -> c?.mapToDomain() }.collect(Collectors.toList<Cousine>())
    }
}