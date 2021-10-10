package com.avall.kotlin.ms.cousine.consumer.domain.usecase.order

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.model.Order
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.IOrderRepository


@Interactor
open class DeliveryOrderUseCase(repository: IOrderRepository) : UpdateOrderUseCase(repository) {
    override fun updateStatus(order: Order): Order {
        order.delivery()
        return repository.persist(order.delivery())
    }
}