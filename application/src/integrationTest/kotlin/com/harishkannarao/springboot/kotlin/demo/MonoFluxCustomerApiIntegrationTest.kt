package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.client.CustomerApiClient.Companion.CustomerResponseTestDto
import com.harishkannarao.springboot.kotlin.demo.common.client.MonoFluxCustomerApiClient
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class MonoFluxCustomerApiIntegrationTest: AbstractBaseIntTest() {
    @Test
    fun `get customer by id`() {
        val response = MonoFluxCustomerApiClient(createRequestSpec())
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
    fun `get all customers`() {
        val response = MonoFluxCustomerApiClient(createRequestSpec())
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