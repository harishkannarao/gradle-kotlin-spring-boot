package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.restassured.RestAssuredFactory
import com.harishkannarao.springboot.kotlin.demo.properties.TestProperties
import io.restassured.specification.RequestSpecification

abstract class AbstractBaseAcceptanceTest {

    private val testProperties: TestProperties = TestProperties("properties/${resolveEnvironment()}.properties")

    protected fun createRequestSpec(followRedirect: Boolean = true): RequestSpecification {
        return RestAssuredFactory.createRequestSpec(testProperties.applicationBaseUrl(), followRedirect)
    }
    companion object {
        fun resolveEnvironment(): String {
            return System.getenv("TEST_ENVIRONMENT")?:System.getProperty("testEnvironment")?:"local"
        }
    }
}