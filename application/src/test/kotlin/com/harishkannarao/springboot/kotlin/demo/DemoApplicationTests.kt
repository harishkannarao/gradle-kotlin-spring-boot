package com.harishkannarao.springboot.kotlin.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Test
	fun contextLoads() {
		println(System.getProperty("test", "defaultValue"))
	}

}