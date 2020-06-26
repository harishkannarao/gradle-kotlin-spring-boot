package com.harishkannarao.springboot.kotlin.demo.concurrency

class AutoConsumerService(
        private val autoIngestionService: AutoIngestionService
) {
    fun consume() {
        println(autoIngestionService.getList())
    }

    fun observe(bufferSize: Int = 5): Boolean {
        return autoIngestionService.getList().size == bufferSize
    }
}