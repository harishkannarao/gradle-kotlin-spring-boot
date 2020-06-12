package com.harishkannarao.springboot.kotlin.demo.common.client

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

class RootApiClient(
        private val requestSpecification: RequestSpecification) {
    fun get(): Response {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath("/")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get()
    }
}