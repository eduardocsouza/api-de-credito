package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Customer

data class CustomerView(
    val firstName: String,
    val lastName: String,
    val email: String,
    val cpf: String,
    val zipCode: String,
    val street: String

) {
    constructor(customer: Customer) : this(
        firstName = customer.firstName,
        lastName = customer.lastName,
        email = customer.email,
        cpf = customer.cpf,
        zipCode = customer.address.zipCode,
        street = customer.address.street
    )
}
