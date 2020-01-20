package com.jetlaunch.revoluttest.core


import com.jetlaunch.revoluttest.models.Currency
import com.jetlaunch.revoluttest.times
import kotlin.collections.ArrayList

class Exchange {
    fun calculate(baseRate: Float, currency: List<Currency>): ArrayList<Currency>{
        return ArrayList(currency.map { it * baseRate })
    }
}