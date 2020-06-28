package com.harishkannarao.springboot.kotlin.demo.controller

import com.harishkannarao.springboot.kotlin.demo.dto.CustomerResponseDto
import kotlinx.coroutines.flow.Flow
import org.javamoney.moneta.Money
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import java.math.BigDecimal
import javax.money.Monetary
import javax.money.MonetaryAmount

@Suppress("unused")
@RestController
@RequestMapping(path = ["/currency"])
class CustomCurrencyController {
    private val customCurrency: MonetaryAmount = Money.of(BigDecimal.valueOf(2.001), Monetary.getCurrency("HCC"))

    @GetMapping
    suspend fun get(
            serverWebExchange: ServerWebExchange
    ): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok().body(mapOf<String, Any>(
                Pair("value", customCurrency.number),
                Pair("code", customCurrency.currency.currencyCode)
        ))
    }
}