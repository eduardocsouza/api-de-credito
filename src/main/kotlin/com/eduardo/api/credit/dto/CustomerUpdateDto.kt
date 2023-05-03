package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Customer

data class CustomerUpdateDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val zipCode: String,
    val street: String
) {
    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.email = this.email
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
