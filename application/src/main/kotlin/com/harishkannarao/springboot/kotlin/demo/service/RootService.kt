package com.harishkannarao.springboot.kotlin.demo.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RootService(
        @Value("\${app.message}") private val message: String
) {

    suspend fun getEntity(): Map<String, String> {
        return mapOf(
                Pair("message", message)
        )
    }
}