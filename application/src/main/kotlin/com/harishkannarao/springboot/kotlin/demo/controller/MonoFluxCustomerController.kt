package com.harishkannarao.springboot.kotlin.demo.controller

import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import com.harishkannarao.springboot.kotlin.demo.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Suppress("unused")
@RestController
@RequestMapping(path = ["/mono-flux/customer"])
class MonoFluxCustomerController(
        private val customerService: CustomerService
) {
    @GetMapping
    fun getAll(
            serverWebExchange: ServerWebExchange
    ): ResponseEntity<Flux<CustomerResponseDto>> {
        return ResponseEntity.ok().body(customerService.getAllAsFlux())
    }

    @GetMapping(path = ["/{id}"])
    fun getById(
            @PathVariable("id") id: String,
            serverWebExchange: ServerWebExchange
    ): ResponseEntity<Mono<CustomerResponseDto>> {
        return ResponseEntity.ok().body(customerService.getByIdAsMono(id))
    }
}