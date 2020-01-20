package com.jetlaunch.revoluttest.models

import java.util.*

data class CurrencyResponse (val base: String, val date: String, val rates: Map<String, Float>)