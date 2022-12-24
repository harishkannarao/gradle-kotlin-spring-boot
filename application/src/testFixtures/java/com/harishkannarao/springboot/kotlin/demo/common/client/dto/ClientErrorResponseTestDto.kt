package com.harishkannarao.springboot.kotlin.demo.common.client.dto

data class ClientErrorResponseTestDto(
        val message: String,
        val errors: List<ClientError>
) {
    data class ClientError(
            val key: String,
            val message: String
    )
}

