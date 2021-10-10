package com.avall.kotlin.ms.cousine.consumer.api

import com.avall.kotlin.ms.cousine.consumer.api.dto.request.OrderRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.UserPrincipal
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ApiResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CustomerResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.OrderResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/order")
interface OrderResource {
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    fun create(
        @CurrentUser userPrincipal: UserPrincipal?,
        httpServletRequest: HttpServletRequest?,
        @RequestBody orderRequest: @Valid OrderRequest?
    ): CompletableFuture<ResponseEntity<OrderResponse?>?>?

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    fun getById(@PathVariable id: String): CompletableFuture<OrderResponse?>?

    @GetMapping("/{id}/customer")
    @PreAuthorize("hasRole('USER')")
    fun getCustomerById(@PathVariable id: String): CompletableFuture<CustomerResponse?>?

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    fun delete(@PathVariable id: String): CompletableFuture<ApiResponse?>?

    @PostMapping("/{id}/payment")
    @PreAuthorize("hasRole('USER')")
    fun pay(@PathVariable id: String): CompletableFuture<ApiResponse?>?

    @PostMapping("/{id}/delivery")
    @PreAuthorize("hasRole('USER')")
    fun delivery(@PathVariable id: String): CompletableFuture<ApiResponse?>?
}