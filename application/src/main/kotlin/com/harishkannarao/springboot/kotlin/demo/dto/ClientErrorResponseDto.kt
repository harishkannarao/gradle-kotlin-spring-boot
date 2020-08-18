package com.harishkannarao.springboot.kotlin.demo.dto

data class ClientErrorResponseDto(
        val message: String,
        val errors: List<ClientError>
) {
    data class ClientError(
            val key: String,
            val message: String
    )
}

