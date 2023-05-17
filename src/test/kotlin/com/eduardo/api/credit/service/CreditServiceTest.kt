package com.eduardo.api.credit.service

import com.eduardo.api.credit.entity.Address
import com.eduardo.api.credit.entity.Credit
import com.eduardo.api.credit.entity.Customer
import com.eduardo.api.credit.enuns.Status
import com.eduardo.api.credit.reppository.CreditRepository
import com.eduardo.api.credit.reppository.CustomerRepository
import com.eduardo.api.credit.service.impl.CreditService
import com.eduardo.api.credit.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.aspectj.lang.annotation.Before
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun `Criado um Crédito`(){


        val fakeCredit: Credit = buildCredit(customer = buildCustomer())

        every { creditRepository.save(any()) } returns fakeCredit

        val actualCredit: Credit = creditService.save(fakeCredit)




        Assertions.assertThat(actualCredit).isNotNull
    }


    private fun buildCredit(
        creditCode: UUID = UUID.randomUUID(),
        creditValue: BigDecimal = BigDecimal("500.0"),
        dayFirstInstallment: LocalDate? = LocalDate.of(2023, 6, 2),
        numberOfInstallments: Int = 12,
        customer: Customer
    ) = Credit(
        creditCode = creditCode,
        creditValue = creditValue,
        dayFirstInstallment =  dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )

    private fun buildCustomer(
        id: Long = 1L,
        firstName: String = "",
        lastName: String = "",
        cpf: String = "",
        email: String = "",
        password: String = "",
        zipCode: String = "53435190",
        street:  String = "rua"
    ) = Customer(
        id = id,
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        )
    )
}