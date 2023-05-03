package com.eduardo.api.credit.reppository

import com.eduardo.api.credit.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CreditRepository : JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value = "SELECT * FROM credito WHERE id_cliente = ?1", nativeQuery = true)
    fun findAllCredits(customerId: Long): List<Credit>
}