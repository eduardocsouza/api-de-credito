package com.eduardo.api.credit.service.impl

import com.eduardo.api.credit.entity.Customer
import com.eduardo.api.credit.reppository.CustomerRepository
import com.eduardo.api.credit.service.ICustomerService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService{

    override fun save(costumer: Customer): Customer {
        return this.customerRepository.save(costumer)
    }

    override fun finfById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{
            throw RuntimeException("id $id not found!")
    }

    override fun delete(id: Long){
        val customer: Customer = this.finfById(id)
        println(customer.toString())
        this.customerRepository.delete(customer)
    }


}