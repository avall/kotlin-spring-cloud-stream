package com.avall.ms.attachments.domain.usecase.order

import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.Order
import com.avall.ms.attachments.domain.port.output.IOrderRepository
import com.ferraobox.qamyapp.application.core.usecases.order.UpdateOrderUseCase

@Interactor
open class PayOrderUseCase(repository: IOrderRepository) : UpdateOrderUseCase(repository) {
    override fun updateStatus(order: Order): Order {
        return repository.persist(order.pay())
    }
}