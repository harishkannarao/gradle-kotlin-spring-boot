package com.harishkannarao.springboot.kotlin.demo.common.client

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

class MonoFluxCustomerApiClient(
        private val requestSpecification: RequestSpecification
) {

    fun getById(id: String): Response {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath("/mono-flux/customer/{id}")
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get()
    }

    fun getAll(): Response {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath("/mono-flux/customer")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get()
    }

    companion object {
        data class CustomerResponseTestDto(
                val id: String,
                val firstName: String,
                val lastName: String
        )
    }
}