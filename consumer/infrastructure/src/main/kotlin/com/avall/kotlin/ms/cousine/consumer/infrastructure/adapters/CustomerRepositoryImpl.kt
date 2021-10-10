package com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters

import com.avall.kotlin.ms.cousine.consumer.domain.model.Customer
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.ICustomerRepository
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.CustomerDomainDbMapper.mapToDb
import com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper.CustomerDomainDbMapper.mapToDomain
import com.avall.kotlin.ms.cousine.consumer.infrastructure.repository.DbCustomerRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepositoryImpl(
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

    override fun findById(id: String): Optional<Customer> {
        return repository
            .findById(id)
            .map { c -> c?.mapToDomain() }
    }
}