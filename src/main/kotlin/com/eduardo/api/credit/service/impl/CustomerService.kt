package com.eduardo.api.credit.service.impl

import com.eduardo.api.credit.entity.Customer
import com.eduardo.api.credit.exception.MyExceptio
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

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow{
            throw MyExceptio("id $id not found!")
    }

    override fun delete(id: Long){
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }


}