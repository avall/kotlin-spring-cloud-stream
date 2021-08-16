package com.avall.ms.attachments.domain.usecase.order

import com.avall.ms.attachments.arch.annotation.Interactor
import com.avall.ms.attachments.domain.model.Order
import com.avall.ms.attachments.domain.port.output.IOrderRepository


@Interactor
open class DeleteOrderUseCase(repository: IOrderRepository) : UpdateOrderUseCase(repository) {
   override fun updateStatus(order: Order): Order {
        return order.delete()
    }
}