package com.avall.kotlin.ms.cousine.consumer.infrastructure.database


import net.bytebuddy.implementation.bind.annotation.Default
import java.time.Instant
import javax.persistence.*

@Entity(name = "order")
@Table(name = "order")
class OrderDb(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: String?,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    var customer: CustomerDb,

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    var store: StoreDb,

    @Column(nullable = false)
    var total: Double,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = [CascadeType.ALL])
    @Default
    var orderItems: MutableSet<OrderItemDb> = HashSet(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: StatusDb,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant,
) : BaseDbEntity() {
    // TODO: test
    private fun addOrderItem(orderItem: OrderItemDb) {
        orderItems = HashSet()
        orderItems.add(orderItem)
        calculateTotal()
    }

    private fun calculateTotal() {
        total = orderItems.sumOf { it.total }
    }

    companion object {
        // TODO: test
        fun newInstance(
            customer: CustomerDb,
            store: StoreDb,
            orderItems: List<OrderItemDb?>
        ): OrderDb {
            val order = OrderDb(
                id = null,
                customer = customer,
                store = store,
                orderItems = HashSet(),
                total = 0.0,
                status = StatusDb.OPEN,
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            )
            orderItems.forEach { orderItem ->
                if (orderItem != null) {
                    order.addOrderItem(
                        orderItem
                    )
                }
            }
            return order
        }
    }
}