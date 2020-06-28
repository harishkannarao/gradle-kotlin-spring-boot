package com.harishkannarao.springboot.kotlin.demo

import com.harishkannarao.springboot.kotlin.demo.currency.CustomCurrencyUnits
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			CustomCurrencyUnits.registerCustomCurrency()
			runApplication<DemoApplication>(*args)
		}
	}
}