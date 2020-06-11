package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.common.RestAssuredFactory
import io.restassured.specification.RequestSpecification
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Suppress("SameParameterValue")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(profiles = ["integration-test"])
abstract class AbstractBaseIntTest {

    @Value("\${test.application.url}")
    protected lateinit var testApplicationUrl: String

    protected fun createRequestSpec(): RequestSpecification {
        return createRequestSpec(true)
    }

    protected fun createRequestSpec(followRedirect: Boolean): RequestSpecification {
        return RestAssuredFactory.createRequestSpec(testApplicationUrl, followRedirect)
    }
}