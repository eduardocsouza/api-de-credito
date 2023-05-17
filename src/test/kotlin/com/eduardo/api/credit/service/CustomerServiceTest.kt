package com.eduardo.api.credit.service

import com.eduardo.api.credit.entity.Address
import com.eduardo.api.credit.entity.Customer
import com.eduardo.api.credit.exception.MyExceptio
import com.eduardo.api.credit.reppository.CustomerRepository
import com.eduardo.api.credit.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var  customerService: CustomerService

    @Test
    fun `Criando um Cliente`(){
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        val actual: Customer = customerService.save(fakeCustomer)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
    }

    @Test
    fun `Busca por ID`(){
        //given
        val idRandom: Long = Random().nextLong()
        val fakCustomer: Customer = buildCustomer(id = idRandom)
        every { customerRepository.findById(idRandom) } returns Optional.of(fakCustomer)

        //when
        val actual: Customer = customerService.findById(idRandom)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakCustomer)
        verify(exactly = 1) {customerRepository.findById(idRandom)}
    }

    @Test
    fun `Capiturando uma excecao`(){
        val idRandom: Long = Random().nextLong()
        every { customerRepository.findById(idRandom) } returns Optional.empty()

        Assertions.assertThatExceptionOfType(MyExceptio::class.java)
            .isThrownBy { customerService.findById(idRandom) }
            .withMessage("Id $idRandom not found")
        verify(exactly = 1) { customerRepository.findById(idRandom) }
    }

    @Test
    fun `Deletando por ID`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs //<-- O comando (just runs) informa que não tem retorno.
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }


    private fun buildCustomer(
        id: Long = 1L,
        firstName: String = "Eduardo",
        lastName: String = "Souza",
        cpf: String = "09023150481",
        email: String = "eduardo@gmail.com",
        zipCode: String = "53435190",
        street:  String = "rua"
    ) = Customer(
        id = id,
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        address = Address(
            street = street,
            zipCode = zipCode
        )



    )

}