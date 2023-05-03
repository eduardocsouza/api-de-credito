package com.eduardo.api.credit.controller

import com.eduardo.api.credit.dto.CreditDto
import com.eduardo.api.credit.dto.CreditView
import com.eduardo.api.credit.dto.CreditViewList
import com.eduardo.api.credit.entity.Credit
import com.eduardo.api.credit.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {

    @PostMapping("/save")
    fun saveCredit(@RequestBody creditDto: CreditDto): String{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam (value = "customerId") customerId: Long) : List<CreditViewList> {
        return this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
    }

    @GetMapping
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                       @PathVariable creditCode: UUID) : CreditView {
        val credit: Credit = this.creditService.findByCredit(customerId, creditCode)
        return CreditView(credit)
    }
}