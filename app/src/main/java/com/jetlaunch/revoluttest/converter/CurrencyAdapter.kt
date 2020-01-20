package com.jetlaunch.revoluttest.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetlaunch.revoluttest.R
import com.jetlaunch.revoluttest.databinding.CurrencyItemBinding
import com.jetlaunch.revoluttest.models.Currency
import com.jetlaunch.revoluttest.utils.CurrencyDiffUtilCallback
import kotlin.collections.ArrayList

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyVH>(){

    var data: List<Currency> = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private var  click: ((Currency) -> Unit)? = null

    fun setupClickListener(callback: (Currency) -> Unit){
        click = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
    val view  = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return CurrencyVH(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        val item = data[position]
        holder.itemView.setOnClickListener {
            click?.invoke(item)
        }
        holder.binding?.currency = item
    }

    class CurrencyVH(itemView: View) : RecyclerView.ViewHolder(itemView){
       val binding = DataBindingUtil.bind<CurrencyItemBinding>(itemView)
    }
}