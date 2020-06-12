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
        return properties.getProperty("application.baseUrl")
    }
}