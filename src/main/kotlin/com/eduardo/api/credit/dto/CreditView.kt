package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Credit
import com.eduardo.api.credit.enuns.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int,
    val status: Status,
    val emaiCustomer: String?
) {
    constructor(credit: Credit) : this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallment = credit.numberOfInstallments,
        status = credit.status,
        emaiCustomer = credit.customer?.email
    )
}
