package com.jetlaunch.revoluttest

import com.jetlaunch.revoluttest.models.Currency

operator fun Currency.times(baseRate: Float): Currency{
    return this.copy(name = this.name, rate = this.rate * baseRate)
}