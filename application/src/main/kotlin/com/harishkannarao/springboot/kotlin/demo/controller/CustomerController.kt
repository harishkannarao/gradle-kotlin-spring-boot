package com.harishkannarao.springboot.kotlin.demo.controller

import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import com.harishkannarao.springboot.kotlin.demo.service.CustomerService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebExchange

@Suppress("unused")
@RestController
@RequestMapping(path = ["/customer"])
class CustomerController(
        private val customerService: CustomerService
) {
    @GetMapping
    suspend fun getAll(
            serverWebExchange: ServerWebExchange
    ): ResponseEntity<Flow<CustomerResponseDto>> {
        return ResponseEntity.ok().body(customerService.getAll())
    }

    @GetMapping(path = ["/{id}"])
    suspend fun getById(
            @PathVariable("id") id: String,
            serverWebExchange: ServerWebExchange
    ): ResponseEntity<CustomerResponseDto> {
        return ResponseEntity.ok().body(customerService.getById(id))
    }
}