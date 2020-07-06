package com.harishkannarao.springboot.kotlin.demo.dao

import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Component

@Component
class CustomerRepository {
    suspend fun getById(id: String): CustomerResponseDto {
        return CustomerResponseDto(
                id = id,
                firstName = "firstName-$id",
                lastName = "lastName-$id"
        )
    }

    suspend fun getAll(): Flow<CustomerResponseDto> = coroutineScope {
        val customer1 = async { getById("1") }
        val customer2 = async { getById("2") }
        sequenceOf(customer1.await(), customer2.await()).asFlow()
    }
}