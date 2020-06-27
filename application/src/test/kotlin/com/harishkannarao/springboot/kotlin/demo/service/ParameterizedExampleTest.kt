package com.harishkannarao.springboot.kotlin.demo.service

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class ParameterizedExampleTest {

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    internal fun `sample parameter test`(
            string: String,
            expectedStringLength: Int,
            stringList: List<String>,
            expectedString: String
    ) {
        assertThat(string.length, equalTo(expectedStringLength))
        assertThat(stringList.joinToString(""), equalTo(expectedString))
    }

    companion object {
        @JvmStatic
        fun stringIntAndListProvider(): Stream<Arguments> {
            return Stream.of(
                    arguments("apple", 5, listOf("a", "b"), "ab"),
                    arguments("orange", 6, listOf("x", "y"), "xy")
            )
        }
    }
}