package com.harishkannarao.springboot.kotlin.demo.service

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class FoldAndReduceTest {

    @Test
    internal fun `fold on list`() {
        val result = listOf("a", "b", "c", "d").fold("^", { acc, s -> acc + s })
        assertThat(result, equalTo("^abcd"))
    }

    @Test
    internal fun `reduce on list`() {
        val result = listOf("a", "b", "c", "d").reduce { acc, s -> acc + s }
        assertThat(result, equalTo("abcd"))
    }

    @Test
    internal fun `fold right on list`() {
        val result = listOf("a", "b", "c", "d").foldRight("^", {s, acc -> acc + s })
        assertThat(result, equalTo("^dcba"))
    }

    @Test
    internal fun `reduce right on list`() {
        val result = listOf("a", "b", "c", "d").reduceRight { s, acc -> acc + s }
        assertThat(result, equalTo("dcba"))
    }

    @Test
    internal fun `fold on map`() {
        val result = mapOf(
                Pair(1, "one"),
                Pair(4, "two")
        ).entries.fold(1, { acc, entry -> acc * entry.key })

        assertThat(result, equalTo(4))
    }

    @Test
    internal fun `fold right on map`() {
        val result = mapOf(
                Pair(1, "one"),
                Pair(4, "two")
        ).toList().foldRight(">", { pair: Pair<Int, String>, acc: String -> acc + pair.second + ">" })
        assertThat(result, equalTo(">two>one>"))
    }
}