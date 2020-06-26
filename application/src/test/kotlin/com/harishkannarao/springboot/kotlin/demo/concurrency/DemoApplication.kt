package com.harishkannarao.springboot.kotlin.demo.concurrency

import kotlinx.coroutines.*

class DemoApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val autoIngestionService = AutoIngestionService(
                        intervalInMillis = 1500L,
                        bufferSize = 5
                )
                val autoConsumerService = AutoConsumerService(
                        autoIngestionService = autoIngestionService,
                        intervalInMillis = 500L
                )
                val ingestionJob = launch { // launch a new coroutine in background and continue
                    autoIngestionService.start()
                }
                val consumerJob = launch { // launch a new coroutine in background and continue
                    autoConsumerService.start()
                }
                while (true) {
                    val result = GlobalScope.async {
                        autoConsumerService.observe(5)
                    }
                    if (result.await()) {
                        ingestionJob.cancelAndJoin()
                        consumerJob.cancelAndJoin()
                        break
                    }
                }
            }
        }
    }
}