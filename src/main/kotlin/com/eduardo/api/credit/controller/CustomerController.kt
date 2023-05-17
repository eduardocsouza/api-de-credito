package com.eduardo.api.credit.controller

import com.eduardo.api.credit.dto.CustomerDto
import com.eduardo.api.credit.dto.CustomerUpdateDto
import com.eduardo.api.credit.dto.CustomerView
import com.eduardo.api.credit.entity.Customer
import com.eduardo.api.credit.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping("/save")
    fun save(@RequestBody @Valid customer: CustomerDto) : ResponseEntity<String>{
        val saveCustomer = this.customerService.save(customer.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${saveCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<HttpStatus>{
        this.customerService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }


    @PatchMapping
    fun updateCustomer(@RequestParam(value = "id_cliente") id: Long,
                       @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView>{
        val customer: Customer = this.customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}