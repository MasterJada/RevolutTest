package com.jetlaunch.revoluttest

import com.jetlaunch.revoluttest.core.Exchange
import com.jetlaunch.revoluttest.models.Currency
import org.junit.Assert
import org.junit.Test


class ExchangeTest {
   private val base = arrayListOf(
        Currency("ICO", 1F),
        Currency("ISO", 2F),
        Currency("HEY", 3F)
        )
   private val exchange = Exchange()

    @Test
    fun `simple test`(){
        val baseCurrency = Currency("EUR", 1F)
        Assert.assertArrayEquals(base.toTypedArray(), exchange.calculate(baseCurrency.rate, base).toTypedArray() )
    }

    @Test
    fun `test in work`(){
        val baseCurrency = Currency("EUR", 2F)
        val expected = arrayListOf(
            Currency("ICO", 2F),
            Currency("ISO", 4F),
            Currency("HEY", 6F)
        )

        Assert.assertArrayEquals(expected.toTypedArray(), exchange.calculate(baseCurrency.rate, base).toTypedArray())
        Assert.assertNotEquals(expected, base)
    }
}