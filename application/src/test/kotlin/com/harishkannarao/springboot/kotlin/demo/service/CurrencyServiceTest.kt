package com.harishkannarao.springboot.kotlin.demo.service

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.javamoney.moneta.Money
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import javax.money.Monetary

class CurrencyServiceTest {

    private val currencyService = CurrencyService()

    @Test
    internal fun `converts the currency by exchange rate`() {
        val input = Money.of(BigDecimal.valueOf(11), Monetary.getCurrency("INR"))
        val exchangeRate = BigDecimal.valueOf(0.0106578)
        val exchangeCurrency = Monetary.getCurrency("GBP")
        val expected = Money.of(BigDecimal.valueOf(0.1172358), exchangeCurrency)

        val result = currencyService.convertCurrency(input, exchangeRate, exchangeCurrency)

        assertThat(result.numberStripped, equalTo(expected.numberStripped))
        assertThat(result, equalTo(expected))
    }

    @Test
    internal fun `converts the currency by exchange rate with default rounding`() {
        val input = Money.of(BigDecimal.valueOf(11), Monetary.getCurrency("INR"))
        val exchangeRate = BigDecimal.valueOf(0.0106578)
        val exchangeCurrency = Monetary.getCurrency("GBP")
        val expected = Money.of(BigDecimal.valueOf(0.12), exchangeCurrency)

        val result = currencyService.convertCurrencyWithRounding(input, exchangeRate, exchangeCurrency)

        assertThat(result.numberStripped, equalTo(expected.numberStripped))
        assertThat(result, equalTo(expected))
    }

    @Test
    internal fun `converts the currency by exchange rate with half up rounding`() {
        val input = Money.of(BigDecimal.valueOf(11), Monetary.getCurrency("INR"))
        val exchangeRate = BigDecimal.valueOf(0.0106578)
        val exchangeCurrency = Monetary.getCurrency("GBP")
        val expected = Money.of(BigDecimal.valueOf(0.12), exchangeCurrency)

        val result = currencyService.convertCurrencyWithRounding(input, exchangeRate, exchangeCurrency,  RoundingMode.HALF_UP)

        assertThat(result.numberStripped, equalTo(expected.numberStripped))
        assertThat(result, equalTo(expected))
    }

    @Test
    internal fun `converts the currency by exchange rate with floor rounding`() {
        val input = Money.of(BigDecimal.valueOf(11), Monetary.getCurrency("INR"))
        val exchangeRate = BigDecimal.valueOf(0.0106578)
        val exchangeCurrency = Monetary.getCurrency("GBP")
        val expected = Money.of(BigDecimal.valueOf(0.11), exchangeCurrency)

        val result = currencyService.convertCurrencyWithRounding(input, exchangeRate, exchangeCurrency, RoundingMode.FLOOR)

        assertThat(result.numberStripped, equalTo(expected.numberStripped))
        assertThat(result, equalTo(expected))
    }

    @Test
    internal fun `divides the money with remainder`() {
        val money = Money.of(BigDecimal.valueOf(1), Monetary.getCurrency("INR"))
        val by = BigDecimal.valueOf(3)
        val expectedShare = Money.of(BigDecimal.valueOf(0.33), Monetary.getCurrency("INR"))
        val expectedRemainder = Money.of(BigDecimal.valueOf(0.01), Monetary.getCurrency("INR"))
        val (share, remainder) = currencyService.divideCurrency(money, by)
        assertThat(share.numberStripped, equalTo(expectedShare.numberStripped))
        assertThat(share, equalTo(expectedShare))
        assertThat(remainder.numberStripped, equalTo(expectedRemainder.numberStripped))
        assertThat(remainder, equalTo(expectedRemainder))
    }
}