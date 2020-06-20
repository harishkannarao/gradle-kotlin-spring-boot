package com.harishkannarao.springboot.kotlin.demo.dao

import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
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

    suspend fun getAll(): Flow<CustomerResponseDto> {
        return listOf(
                CustomerResponseDto(
                        id = "1",
                        firstName = "firstName-1",
                        lastName = "lastName-1"
                ),
                CustomerResponseDto(
                        id = "2",
                        firstName = "firstName-2",
                        lastName = "lastName-2"
                )
        ).asFlow()
    }
}