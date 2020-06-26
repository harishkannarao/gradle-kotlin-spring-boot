package com.harishkannarao.springboot.kotlin.demo.concurrency

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DemoApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val autoIngestionService = AutoIngestionService(
                    intervalInMillis = 1500L,
                    bufferSize = 5
            )
            val autoConsumerService = AutoConsumerService(
                    autoIngestionService = autoIngestionService,
                    intervalInMillis = 500L
            )
            GlobalScope.launch { // launch a new coroutine in background and continue
                autoIngestionService.start()
            }
            GlobalScope.launch { // launch a new coroutine in background and continue
                autoConsumerService.start()
            }
            runBlocking {
                while (true) {
                    val result = GlobalScope.async {
                        autoConsumerService.observe(5)
                    }
                    if (result.await()) {
                        break
                    }
                }
            }
        }
    }
}