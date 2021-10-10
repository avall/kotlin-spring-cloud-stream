package com.avall.kotlin.ms.cousine.consumer.api

import com.avall.kotlin.ms.cousine.consumer.api.dto.request.SignInRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.SignUpRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.AuthenticationResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CustomerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/customer")
interface CustomerResource {
    @PostMapping
    fun signUp(
        @RequestBody signUpRequest: @Valid SignUpRequest, httpServletRequest: HttpServletRequest
    ): CompletableFuture<ResponseEntity<CustomerResponse>>

    @PostMapping("/auth")
    fun signIn(@RequestBody signInRequest: @Valid SignInRequest): CompletableFuture<ResponseEntity<AuthenticationResponse>>
}