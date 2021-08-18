package com.avall.ms.attachments.infrastructure.adapters

import com.avall.ms.attachments.domain.port.output.ICustomerRepository
import com.avall.ms.attachments.infrastructure.mapper.CustomerDomainDbMapper.mapToDb
import com.avall.ms.attachments.infrastructure.security.UserPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CustomUserDetailsService(private val customerRepository: ICustomerRepository) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val customerData = customerRepository
            .findByEmail(email)
            .orElseThrow { UsernameNotFoundException(String.format("User %s not found", email)) }
        return UserPrincipal.from(customerData.mapToDb())
    }

    @Transactional
    fun loadUserById(id: String): UserDetails {
        val customer = customerRepository
            .findById(id)
            .orElseThrow { UsernameNotFoundException(String.format("User %s not found", id)) }
        return UserPrincipal.from(customer.mapToDb())
    }
}