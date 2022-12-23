package com.harishkannarao.springboot.kotlin.demo.common.restassured

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.RedirectConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import java.net.URI

object RestAssuredFactory {
    fun createRequestSpec(baseUrl: String, followRedirect: Boolean): RequestSpecification {
        return RequestSpecBuilder()
                .setBaseUri(URI(baseUrl))
                .setConfig(createRestAssuredConfig(followRedirect))
                .addFilter(RequestLoggingFilter(LogDetail.ALL))
                .addFilter(ResponseLoggingFilter(LogDetail.ALL))
                .build()
    }

    private fun createRestAssuredConfig(followRedirect: Boolean): RestAssuredConfig {
        val restAssuredConfig = RestAssuredConfig.config()
                .redirect(createRedirectConfig(followRedirect))
        return CurlRestAssuredConfigFactory.updateConfig(restAssuredConfig)
    }

    private fun createRedirectConfig(followRedirect: Boolean): RedirectConfig {
        return RedirectConfig.redirectConfig().followRedirects(followRedirect)
    }
}