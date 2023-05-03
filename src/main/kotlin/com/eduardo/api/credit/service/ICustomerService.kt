package com.eduardo.api.credit.service

import com.eduardo.api.credit.entity.Customer


interface ICustomerService {

    fun save(costumer: Customer): Customer
    fun finfById(id: Long): Customer
    fun delete(id: Long)

}