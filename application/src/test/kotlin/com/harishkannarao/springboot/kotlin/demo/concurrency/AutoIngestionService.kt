package com.harishkannarao.springboot.kotlin.demo.concurrency

import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.ZoneOffset

class AutoIngestionService(
        private val bufferSize: Int = 3
) {

    private var list: List<Pair<OffsetDateTime, BigDecimal>> = emptyList()

    fun getList(): List<Pair<OffsetDateTime, BigDecimal>> {
        return list
    }

    fun ingest() {
        list = list.plus(Pair(OffsetDateTime.now(ZoneOffset.UTC), BigDecimal.valueOf(Math.random())))
                .takeLast(bufferSize)
    }
}