package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.RestAssuredFactory
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class SampleAcceptanceTest {
    @Test
    fun `a sample qa test`() {
        println(System.getProperty("test", "defaultValue"))
        val requestSpec = RestAssuredFactory.createRequestSpec("http://localhost:8080", true)

        val response = RestAssured.given()
                .spec(requestSpec)
                .basePath("/")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get()

        assertThat(response.statusCode, equalTo(200))
        assertThat(response.jsonPath().getString("message"), equalTo("success"))
    }
}