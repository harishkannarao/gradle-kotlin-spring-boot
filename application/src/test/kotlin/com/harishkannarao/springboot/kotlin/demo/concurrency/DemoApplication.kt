package com.harishkannarao.springboot.kotlin.demo.concurrency

import kotlinx.coroutines.*

class DemoApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val autoIngestionService = AutoIngestionService(
                        bufferSize = 5
                )
                val autoConsumerService = AutoConsumerService(
                        autoIngestionService = autoIngestionService
                )
                val ingestionJob = launch { // launch a new coroutine in background and continue
                    while (isActive) {
                        autoIngestionService.ingest()
                        delay(1500L)
                    }
                }
                val consumerJob = launch { // launch a new coroutine in background and continue
                    while (isActive) {
                        autoConsumerService.consume()
                        delay(500L)
                    }
                }
                while (true) {
                    val result = async {
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