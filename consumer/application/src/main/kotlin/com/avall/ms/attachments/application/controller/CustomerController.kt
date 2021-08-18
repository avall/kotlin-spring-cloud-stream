package com.avall.ms.attachments.application.controller

import com.avall.ms.attachments.api.CustomerResource
import com.avall.ms.attachments.api.dto.request.SignInRequest
import com.avall.ms.attachments.api.dto.request.SignUpRequest
import com.avall.ms.attachments.api.dto.response.AuthenticationResponse
import com.avall.ms.attachments.api.dto.response.CustomerResponse
import com.avall.ms.attachments.application.mapper.domainDto.CustomerDomainDtoMapper.mapToDto
import com.avall.ms.attachments.application.mapper.inputOutputDto.AuthenticateCustomerUseCaseInputMapper.map
import com.avall.ms.attachments.application.mapper.inputOutputDto.AuthenticateCustomerUseCaseOutputMapper.map
import com.avall.ms.attachments.application.mapper.inputOutputDto.CreateCustomerInputMapper.map
import com.avall.ms.attachments.arch.usecase.UseCaseExecutor
import com.avall.ms.attachments.domain.usecase.customer.CreateCustomerUseCase
import com.avall.ms.attachments.domain.usecase.security.AuthenticateCustomerUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Component
class CustomerController(
    private val useCaseExecutor: UseCaseExecutor,
    private val createCustomerUseCase: CreateCustomerUseCase,
    private val authenticateCustomerUseCase: AuthenticateCustomerUseCase,
    //private val publishService: PublisherService
) : CustomerResource {

    override fun signUp(
        @RequestBody signUpRequest: @Valid SignUpRequest,
        httpServletRequest: HttpServletRequest
    ): CompletableFuture<ResponseEntity<CustomerResponse>> {
        //publishService.send("created user event", "execute-producer-out-0")
        return useCaseExecutor.execute(
            createCustomerUseCase,
            signUpRequest.map()
        ) { outputValues ->
            val location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("customer/{id}")
                .buildAndExpand(outputValues.customer.id)
                .toUri()
            ResponseEntity.created(location).body(outputValues.customer.mapToDto())
        }
    }

    override fun signIn(@RequestBody signInRequest: @Valid SignInRequest): CompletableFuture<ResponseEntity<AuthenticationResponse>> {
        return useCaseExecutor.execute(
            authenticateCustomerUseCase,
            signInRequest.map()
        ) { outputValues -> ResponseEntity.ok(outputValues.map()) }
    }
}