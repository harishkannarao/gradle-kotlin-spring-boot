package com.harishkannarao.springboot.kotlin.demo.concurrency

import kotlinx.coroutines.delay

class AutoConsumerService(
        private val autoIngestionService: AutoIngestionService,
        private val intervalInMillis: Long = 2500L
) {
    suspend fun start() {
        while (true) {
            consume()
            delay(intervalInMillis)
        }
    }

    fun consume() {
        println(autoIngestionService.getList())
    }

    fun observe(bufferSize: Int = 5): Boolean {
        return autoIngestionService.getList().size == bufferSize
    }
}