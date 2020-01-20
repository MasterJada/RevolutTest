package com.jetlaunch.revoluttest.models

data class Currency(val name: String, var rate: Float, var editable: Boolean = false){
    var strReate: String
    get() = rate.toString()
    set(value) {
        rate = if(value.isEmpty()) 0F else value.toFloatOrNull() ?: 0F
    }
}

