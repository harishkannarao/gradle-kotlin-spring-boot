package com.harishkannarao.springboot.kotlin.demo

import org.junit.jupiter.api.Test

class AsyncFlowTest {
    @Test
    internal fun `test sequence`() {
        foo().forEach { value -> println(value) }
    }

    fun foo(): Sequence<Int> = sequence { // sequence builder
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it
            yield(i) // yield next value
        }
    }
}