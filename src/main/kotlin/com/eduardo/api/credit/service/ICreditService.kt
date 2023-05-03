package com.eduardo.api.credit.service

import com.eduardo.api.credit.entity.Credit
import java.util.UUID

interface ICreditService {

    fun save(credit: Credit): Credit
    fun findAllByCustomer(CustomerId: Long): List<Credit>
    fun findByCredit(customerId: Long,creditCode: UUID): Credit


}