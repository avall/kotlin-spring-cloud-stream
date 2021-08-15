package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.model.Customer
import com.avall.ms.attachments.domain.port.output.ICustomerRepository
import com.avall.ms.attachments.infrastructure.mapper.CustomerDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.mapper.CustomerDomainDbMapper.mapToDomain
import com.avall.ms.attachments.infrastructure.repository.DbCustomerRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class CustomerRepositoryImpl(
    private val repository: DbCustomerRepository,
) : ICustomerRepository {

    override fun persist(customer: Customer): Customer {
        return repository.save(customer.mapToDb()).mapToDomain()
    }

    override fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    override fun findByEmail(email: String): Optional<Customer> {
        return repository
            .findByEmail(email)
            .map { c -> c?.mapToDomain() }
    }

    override fun findById(id: Long): Optional<Customer> {
        return repository
            .findById(id)
            .map { c -> c?.mapToDomain() }
    }
}