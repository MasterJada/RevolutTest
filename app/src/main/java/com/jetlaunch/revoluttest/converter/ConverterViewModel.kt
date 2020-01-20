package com.jetlaunch.revoluttest.converter


import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetlaunch.revoluttest.core.Exchange
import com.jetlaunch.revoluttest.models.Currency
import com.jetlaunch.revoluttest.network.NetworkLiveData


class ConverterViewModel : ViewModel() {
   private val exchange = Exchange()

    val baseCurrency = MutableLiveData<Currency>()

    fun setupBaseCurrency(currency: Currency){
        baseCurrency.postValue(currency.copy(editable = true))
        networkData.updateBaseCurrency(currency.name)
    }

    val networkData = NetworkLiveData()

    val currencyRates = MutableLiveData<String>()

    val mediatorLiveData = MediatorLiveData<List<Currency>>().apply {
        addSource(networkData){
           postValue(calculateRate())
        }
        addSource(currencyRates){
            postValue(calculateRate())
        }
    }


    fun upd(text: String){
        currencyRates.postValue(text)
    }
    private  fun calculateRate(): ArrayList<Currency>{
      return exchange.calculate(baseCurrency.value?.rate ?: 0F, networkData.value ?: emptyList())
    }

}
