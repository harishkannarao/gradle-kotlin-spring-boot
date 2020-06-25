package com.harishkannarao.springboot.kotlin.demo.service

import org.javamoney.moneta.Money
import java.math.BigDecimal
import java.math.RoundingMode
import javax.money.CurrencyUnit
import javax.money.Monetary
import javax.money.RoundingQueryBuilder

class CurrencyService {

    fun divideCurrency(money: Money, by: BigDecimal): Pair<Money, Money> {
        val rounding = Monetary.getRounding(
                RoundingQueryBuilder.of()
                        .setScale(money.currency.defaultFractionDigits)
                        .set(RoundingMode.HALF_UP)
                        .build()
        )
        val share = money.divide(by).with(rounding)
        val remainder = money.subtract(share.multiply(by))
        return Pair(share, remainder)
    }

    fun convertCurrency(input: Money, exchangeRate: BigDecimal, exchangeCurrency: CurrencyUnit): Money {
        input.multiply(exchangeRate).number
        return Money.of(input.multiply(exchangeRate).number, exchangeCurrency)
    }

    fun convertCurrencyWithRounding(input: Money, exchangeRate: BigDecimal, exchangeCurrency: CurrencyUnit, roundingMode: RoundingMode = RoundingMode.HALF_UP): Money {
        val rounding = Monetary.getRounding(
                RoundingQueryBuilder.of()
                        .setScale(exchangeCurrency.defaultFractionDigits)
                        .set(roundingMode)
                        .build()
        )
        return convertCurrency(input, exchangeRate, exchangeCurrency).with(rounding)
    }
}