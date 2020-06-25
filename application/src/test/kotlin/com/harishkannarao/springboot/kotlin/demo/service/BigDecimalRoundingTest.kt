package com.harishkannarao.springboot.kotlin.demo.service

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalRoundingTest {

    @Test
    internal fun `rounding ceil test`() {
        assertThat(BigDecimal.valueOf(2.550).setScale(2, RoundingMode.CEILING), equalTo(BigDecimal.valueOf(2.55)))
        assertThat(BigDecimal.valueOf(2.551).setScale(2, RoundingMode.CEILING), equalTo(BigDecimal.valueOf(2.56)))
        assertThat(BigDecimal.valueOf(2.559).setScale(2, RoundingMode.CEILING), equalTo(BigDecimal.valueOf(2.56)))
    }

    @Test
    internal fun `rounding floor test`() {
        assertThat(BigDecimal.valueOf(2.550).setScale(2, RoundingMode.FLOOR), equalTo(BigDecimal.valueOf(2.55)))
        assertThat(BigDecimal.valueOf(2.559).setScale(2, RoundingMode.FLOOR), equalTo(BigDecimal.valueOf(2.55)))
    }

    @Test
    internal fun `rounding half up test`() {
        assertThat(BigDecimal.valueOf(2.554).setScale(2, RoundingMode.HALF_UP), equalTo(BigDecimal.valueOf(2.55)))
        assertThat(BigDecimal.valueOf(2.555).setScale(2, RoundingMode.HALF_UP), equalTo(BigDecimal.valueOf(2.56)))
        assertThat(BigDecimal.valueOf(2.556).setScale(2, RoundingMode.HALF_UP), equalTo(BigDecimal.valueOf(2.56)))
    }

    @Test
    internal fun `rounding half down test`() {
        assertThat(BigDecimal.valueOf(2.554).setScale(2, RoundingMode.HALF_DOWN), equalTo(BigDecimal.valueOf(2.55)))
        assertThat(BigDecimal.valueOf(2.555).setScale(2, RoundingMode.HALF_DOWN), equalTo(BigDecimal.valueOf(2.55)))
        assertThat(BigDecimal.valueOf(2.556).setScale(2, RoundingMode.HALF_DOWN), equalTo(BigDecimal.valueOf(2.56)))
    }

    @Test
    internal fun `rounding half even test`() {
        assertThat(BigDecimal.valueOf(2.554).setScale(2, RoundingMode.HALF_EVEN), equalTo(BigDecimal.valueOf(2.55)))
        assertThat(BigDecimal.valueOf(2.545).setScale(2, RoundingMode.HALF_EVEN), equalTo(BigDecimal.valueOf(2.54)))
        assertThat(BigDecimal.valueOf(2.555).setScale(2, RoundingMode.HALF_EVEN), equalTo(BigDecimal.valueOf(2.56)))
        assertThat(BigDecimal.valueOf(2.556).setScale(2, RoundingMode.HALF_EVEN), equalTo(BigDecimal.valueOf(2.56)))
    }
}