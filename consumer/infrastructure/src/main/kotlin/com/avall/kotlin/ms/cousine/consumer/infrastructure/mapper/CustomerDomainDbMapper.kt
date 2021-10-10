package com.avall.kotlin.ms.cousine.consumer.infrastructure.mapper

import com.avall.kotlin.ms.cousine.consumer.domain.model.Customer
import com.avall.kotlin.ms.cousine.consumer.infrastructure.database.CustomerDb


object CustomerDomainDbMapper {

    fun Customer.mapToDb(): CustomerDb {
        return CustomerDb(
            id = this.id,
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
            id = this.id,
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