package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.client.CustomerApiClient
import com.harishkannarao.springboot.kotlin.demo.common.client.CustomerApiClient.Companion.CustomerResponseTestDto
import com.harishkannarao.springboot.kotlin.demo.common.client.dto.ClientErrorResponseTestDto
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class CustomerApiIntegrationTest: AbstractBaseIntTest() {
    @Test
    fun `get customer by id`() {
        val response = CustomerApiClient(createRequestSpec())
                .getById("test-id")

        assertThat(response.statusCode, equalTo(200))

        val result = response.jsonPath().getObject("", CustomerResponseTestDto::class.java)
        val expected = CustomerResponseTestDto(
                id = "test-id",
                firstName = "firstName-test-id",
                lastName = "lastName-test-id"
        )
        assertThat(result, equalTo(expected))
    }

    @Test
    fun `get customer by id returns client error for id less than 2 characters`() {
        val response = CustomerApiClient(createRequestSpec())
                .getById("x")

        assertThat(response.statusCode, equalTo(400))

        val result = response.jsonPath().getObject("", ClientErrorResponseTestDto::class.java)
        assertThat(result.message, equalTo("Client Error"))
        assertThat(result.errors, containsInAnyOrder(ClientErrorResponseTestDto.ClientError("getById.id", "size must be between 2 and 20")))
    }

    @Test
    fun `get all customers`() {
        val response = CustomerApiClient(createRequestSpec())
                .getAll()

        assertThat(response.statusCode, equalTo(200))

        val result = response.jsonPath().getList("", CustomerResponseTestDto::class.java)
        val expected1 = CustomerResponseTestDto(
                id = "1",
                firstName = "firstName-1",
                lastName = "lastName-1"
        )
        val expected2 = CustomerResponseTestDto(
                id = "2",
                firstName = "firstName-2",
                lastName = "lastName-2"
        )
        assertThat(result, containsInAnyOrder(expected1, expected2))
    }
}