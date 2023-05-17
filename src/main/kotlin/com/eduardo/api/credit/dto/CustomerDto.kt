package com.eduardo.api.credit.dto

import com.eduardo.api.credit.entity.Address
import com.eduardo.api.credit.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CPF


data class CustomerDto(
   @field:NotEmpty(message = "Campo vazio")val firstName: String,
   @field:NotEmpty(message = "Campo vazio")val lastName: String,
   @field:CPF(message = "CPF inválido")val cpf: String,
   @field:Email(message = "E-mail inválido")val email: String,
   @field:NotEmpty(message = "Campo vazio")val password: String,
   @field:NotEmpty(message = "Campo vazio")val zipCode: String,
   @field:NotEmpty(message = "Campo vazio")val street: String
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
