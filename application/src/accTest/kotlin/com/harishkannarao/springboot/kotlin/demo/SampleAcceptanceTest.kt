package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.client.RootApiClient
import com.harishkannarao.springboot.kotlin.demo.common.restassured.RestAssuredFactory
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class SampleAcceptanceTest {
    @Test
    fun `a sample qa test`() {
        println(System.getProperty("test", "defaultValue"))
        val requestSpec = RestAssuredFactory.createRequestSpec("http://localhost:8080", true)

        val response = RootApiClient(requestSpec).get()

        assertThat(response.statusCode, equalTo(200))
        assertThat(response.jsonPath().getString("message"), equalTo("success"))
    }
}