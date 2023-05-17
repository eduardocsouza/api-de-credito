package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class CustomerUpdateDto(
    @field:NotNull(message = "Campo vazio") val firstName: String,
    @field:NotNull(message = "Campo vazio") val lastName: String,
    @field:Email(message = "E-mail incorreto") val email: String,
    @field:NotNull(message = "Campo vazio") val zipCode: String,
    @field:NotNull(message = "Campo vazio") val street: String
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
