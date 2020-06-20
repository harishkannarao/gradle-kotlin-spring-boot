package com.harishkannarao.springboot.kotlin.demo.service

import com.harishkannarao.springboot.kotlin.demo.dao.CustomerRepository
import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Component

@Component
class CustomerService(
        private val customerRepository: CustomerRepository
) {

    suspend fun getById(id: String): CustomerResponseDto {
        return customerRepository.getById(id)
    }

    suspend fun getAll(): Flow<CustomerResponseDto> {
        return customerRepository.getAll()
    }
}