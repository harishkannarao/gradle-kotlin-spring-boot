package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.service.RootService
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
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
		runBlocking {
			val result = rootService.getEntity()
			assertThat(result["message"], equalTo("test-message"))
		}
	}
}
