package com.harishkannarao.springboot.kotlin.demo

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class SampleIntegrationTest : AbstractBaseIntTest() {
    @Test
    fun `test hello world message`() {
        println(System.getProperty("test", "defaultValue"))
        val response = RestAssured.given()
                .spec(createRequestSpec())
                .basePath("/")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get()

        assertThat(response.statusCode, equalTo(200))
        assertThat(response.jsonPath().getString("message"), equalTo("success!!!"))
    }
}