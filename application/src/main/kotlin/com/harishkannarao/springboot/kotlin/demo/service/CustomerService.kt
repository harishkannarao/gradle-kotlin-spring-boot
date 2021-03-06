package com.harishkannarao.springboot.kotlin.demo.service

import com.harishkannarao.springboot.kotlin.demo.dao.CustomerRepository
import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.constraints.Size

@Component
@Validated
class CustomerService(
        private val customerRepository: CustomerRepository
) {

    suspend fun getById(@Size(min = 2, max = 20) id: String): CustomerResponseDto {
        return customerRepository.getById(id)
    }

    fun getByIdAsMono(id: String): Mono<CustomerResponseDto> {
        return customerRepository.getByIdAsMono(id)
    }


    suspend fun getAll(): Flow<CustomerResponseDto> {
        return customerRepository.getAll()
    }

    fun getAllAsFlux(): Flux<CustomerResponseDto> {
        return customerRepository.getAllAsFlux()
    }
}