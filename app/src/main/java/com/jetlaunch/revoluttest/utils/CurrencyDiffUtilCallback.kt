package com.jetlaunch.revoluttest.utils

import androidx.recyclerview.widget.DiffUtil
import com.jetlaunch.revoluttest.models.Currency


class CurrencyDiffUtilCallback(
    private val oldList: List<Currency>,
    private val newList: List<Currency>
) : DiffUtil.Callback() {


    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = oldList[oldItemPosition]
        val newCurrency = newList[newItemPosition]
        return  oldCurrency.name == newCurrency.name

    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = oldList[oldItemPosition]
        val newCurrency = newList[newItemPosition]
        return oldCurrency.name == newCurrency.name && oldCurrency.rate == newCurrency.rate
    }
}