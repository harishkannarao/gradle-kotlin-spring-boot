package com.harishkannarao.springboot.kotlin.demo.properties

import java.util.*

class TestProperties(location: String) {
    private val properties: Properties

    init {
        val tempProperties = Properties()
        tempProperties.load(Objects.requireNonNull(javaClass.classLoader.getResourceAsStream(location)))
        properties = tempProperties
    }

    fun applicationBaseUrl(): String {
        val applicationBaseUrl = properties.getProperty("application.baseUrl")
        if (applicationBaseUrl.contains("localhost") && System.getenv().containsKey("MINIKUBE_IP")) {
            return applicationBaseUrl.replace("localhost", System.getenv("MINIKUBE_IP"))
        }
        return applicationBaseUrl
    }
}