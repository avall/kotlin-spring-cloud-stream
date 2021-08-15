package com.avall.ms.attachments.infrastructure.mapper

import com.avall.ms.attachments.domain.model.Customer
import com.avall.ms.attachments.domain.model.Identity
import com.avall.ms.attachments.infrastructure.database.CustomerDb
import java.util.*


object CustomerDomainDbMapper {

    fun Customer.mapToDb(): CustomerDb {
        return CustomerDb(
            id = this.id.number,
            name = this.name,
            email = this.email,
            address = this.address,
            password = this.password
        )
    }

    fun List<Customer>.mapToDb(): List<CustomerDb> {
        val customerDbList = ArrayList<CustomerDb>()
        forEach { customer ->
            customerDbList.add(customer.mapToDb())
        }
        return customerDbList
    }

    fun CustomerDb.mapToDomain(): Customer {
        return Customer(
            id = Identity(this.id!!),
            name = this.name,
            email = this.email,
            address = this.address,
            password = this.password
        )
    }

    fun List<CustomerDb>.mapToDomain(): List<Customer> {
        val customerList = ArrayList<Customer>()
        forEach { customerDB ->
            customerList.add(customerDB.mapToDomain())
        }
        return customerList
    }
}