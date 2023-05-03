package com.eduardo.api.credit.reppository

import com.eduardo.api.credit.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
}