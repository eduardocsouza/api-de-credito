package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Address
import com.eduardo.api.credit.entity.Customer


data class CustomerDto(
   val firstName: String,
   val lastName: String,
   val cpf: String,
   val email: String,
   val password: String,
   val zipCode: String,
   val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
