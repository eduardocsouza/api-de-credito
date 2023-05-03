package com.eduardo.api.credit.service.impl

import com.eduardo.api.credit.entity.Credit
import com.eduardo.api.credit.entity.Customer
import com.eduardo.api.credit.reppository.CreditRepository
import com.eduardo.api.credit.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService (
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.finfById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.creditRepository.findAllCredits(customerId)
    }

    override fun findByCredit(customerId: Long, creditCode: UUID): Credit {
        val credit : Credit = this.creditRepository.findByCreditCode(creditCode) ?: throw RuntimeException("CreditCode: $creditCode not found")
        return if(credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
    }
}