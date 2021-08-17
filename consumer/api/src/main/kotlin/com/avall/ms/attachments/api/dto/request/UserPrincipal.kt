package com.avall.ms.attachments.api.dto.request

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

open class UserPrincipal(
    var id: String?,
    var name: String?,
    var email: String?,
    private var password: String?,
    var address: String?,
    private var authorities: Collection<GrantedAuthority?>?
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?>{
        return authorities!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return name!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

//    companion object {
//        fun from(customer: CustomerDb): UserPrincipal {
//            return UserPrincipal(
//                customer.id,
//                customer.name,
//                customer.email,
//                customer.password,
//                customer.address,
//                listOf(SimpleGrantedAuthority("ROLE_USER"))
//            )
//        }
//    }
}
