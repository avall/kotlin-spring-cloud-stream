package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.Customer
import java.util.*

interface ICustomerRepository {
    fun persist(customer: Customer): Customer
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Customer>
    fun findById(id: String): Optional<Customer>
}