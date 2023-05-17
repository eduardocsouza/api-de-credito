package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Credit
import com.eduardo.api.credit.entity.Customer
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalDate,
    @field:Max(value = 48, message = "Valos de parcela excede o permitido")
    @field:Min(value = 1, message = "Valor mínino é 1")
    val numberOfInstallments: Int,
    @field:NotNull
    val customerId: Long

) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)

    )
}
