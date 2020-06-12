package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.client.RootApiClient
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class SampleAcceptanceTest : AbstractBaseAcceptanceTest() {
    @Test
    fun `a sample qa test`() {
        println(System.getProperty("test", "defaultValue"))

        val response = RootApiClient(createRequestSpec()).get()

        assertThat(response.statusCode, equalTo(200))
        assertThat(response.jsonPath().getString("message"), equalTo("success!!!"))
    }
}