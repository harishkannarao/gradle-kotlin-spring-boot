package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.service.RootService
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SampleUnitTest {
	private lateinit var rootService: RootService

	@BeforeEach
	fun setUp() {
		rootService = RootService("test-message")
	}

	@Test
	fun `getEntity returns message`() {
		val result = rootService.getEntity()
		assertThat(result["message"], equalTo("test-message"))
	}

}
