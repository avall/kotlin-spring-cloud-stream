package com.avall.kotlin.ms.cousine.consumer.application.controller


import com.avall.kotlin.ms.cousine.consumer.api.CurrentUser
import com.avall.kotlin.ms.cousine.consumer.api.OrderResource
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.OrderRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.request.UserPrincipal
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ApiResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CustomerResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.OrderResponse
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.CustomerDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.application.mapper.domainDto.OrderDomainDtoMapper.mapToDto
import com.avall.kotlin.ms.cousine.consumer.application.mapper.inputOutputDto.CreateOrderInputMapper
import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCaseExecutor
import com.avall.kotlin.ms.cousine.consumer.domain.usecase.order.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Component
class OrderController(
    private val useCaseExecutor: UseCaseExecutor,
    private val createOrderUseCase: CreateOrderUseCase,
    private val getOrderUseCase: GetOrderUseCase,
    private val getCustomerOrderUseCase: GetCustomerOrderUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val payOrderUseCase: PayOrderUseCase,
    private val deliveryOrderUseCase: DeliveryOrderUseCase,
    private val createOrderInputMapper: CreateOrderInputMapper,
) : OrderResource {

    override fun create(
        @CurrentUser userPrincipal: UserPrincipal?,
        httpServletRequest: HttpServletRequest?,
        @RequestBody orderRequest: @Valid OrderRequest?
    ): CompletableFuture<ResponseEntity<OrderResponse?>?> {
        return useCaseExecutor.execute(
            createOrderUseCase,
            createOrderInputMapper.map(orderRequest!!, userPrincipal!!)
        ) { outputValues ->
            val location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest!!)
                .path("/order/{id}")
                .buildAndExpand(outputValues.order!!.id)
                .toUri()
            ResponseEntity.created(location).body(outputValues.order!!.mapToDto())
        }
    }

    override fun getById(@PathVariable id: String): CompletableFuture<OrderResponse?> {
        return useCaseExecutor.execute(
            getOrderUseCase,
            GetOrderUseCase.InputValues(id = id)
        ) { outputValues -> outputValues.order!!.mapToDto() }
    }

    override fun getCustomerById(@PathVariable id: String): CompletableFuture<CustomerResponse?> {
        return useCaseExecutor.execute(
            getCustomerOrderUseCase,
            GetCustomerOrderUseCase.InputValues(id = id)
        ) { outputValues -> outputValues.customer!!.mapToDto() }
    }

    override fun delete(@PathVariable id: String): CompletableFuture<ApiResponse?> {
        return useCaseExecutor.execute(
            deleteOrderUseCase,
            UpdateOrderUseCase.InputValues(id = id)
        ) { ApiResponse(true, "Order successfully canceled") }
    }

    override fun pay(@PathVariable id: String): CompletableFuture<ApiResponse?> {
        return useCaseExecutor.execute(
            payOrderUseCase,
            UpdateOrderUseCase.InputValues(id = id)
        ) { ApiResponse(true, "Order successfully paid") }
    }

    override fun delivery(@PathVariable id: String): CompletableFuture<ApiResponse?> {
        return useCaseExecutor.execute(
            deliveryOrderUseCase,
            UpdateOrderUseCase.InputValues(id = id)
        ) { ApiResponse(true, "Order successfully delivered") }
    }
}