package com.harishkannarao.springboot.kotlin.demo.filter

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
@Order(value = Ordered.LOWEST_PRECEDENCE)
class LowerPrecedenceWebFilter(
        private val handlerMapping: RequestMappingHandlerMapping
) : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        val response = exchange.response
        val handler = handlerMapping.getHandler(exchange).toProcessor().peek() as HandlerMethod
        return chain.filter(exchange)
    }
}