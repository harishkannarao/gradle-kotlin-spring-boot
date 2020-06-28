package com.harishkannarao.springboot.kotlin.demo.currency

import org.javamoney.moneta.CurrencyUnitBuilder
import org.javamoney.moneta.spi.ConfigurableCurrencyUnitProvider


object CustomCurrencyUnits {

    fun registerCustomCurrency() {
        val unit = CurrencyUnitBuilder.of("HCC", "CustomCurrencyProvider")
                .setDefaultFractionDigits(3)
                .build()

        ConfigurableCurrencyUnitProvider.registerCurrencyUnit(unit)

    }

    fun unRegisterCustomCurrency() {
        ConfigurableCurrencyUnitProvider.removeCurrencyUnit("HCC")
    }
}