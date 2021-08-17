package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.Customer
import java.util.*

interface ICustomerRepository {
    fun persist(customer: Customer): Customer
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Customer>
    fun findById(id: String): Optional<Customer>
}