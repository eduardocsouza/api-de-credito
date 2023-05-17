package com.eduardo.api.credit.entity

import com.eduardo.api.credit.enuns.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,

    @Column(nullable = false)
    var creditCode: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val creditValue: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val dayFirstInstallment: LocalDate? = null,

    @Column(nullable = false)
    val numberOfInstallments: Int = 0,

    @Column(nullable = false)
    @Enumerated
    val status: Status = Status.IN_PROGRESS,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null,
)
