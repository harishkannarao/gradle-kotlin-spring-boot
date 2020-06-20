package com.harishkannarao.springboot.kotlin.demo.service

import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RootServiceUnitTest {
	private lateinit var rootService: RootService

	@BeforeEach
	internal fun setUp() {
		rootService = RootService("test-message")
	}

	@Test
	internal fun `getEntity returns message`() {
		runBlocking {
			val result = rootService.getEntity()
			assertThat(result["message"], equalTo("test-message"))
		}
	}
}
