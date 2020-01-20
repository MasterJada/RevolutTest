package com.jetlaunch.revoluttest.network

import androidx.lifecycle.LiveData
import com.jetlaunch.revoluttest.models.Currency
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class NetworkLiveData(): LiveData<List<Currency>>() {
    private val compositeDisposable = CompositeDisposable()
    private val api = Api.instance
    private var baseCurrency = "EUR"

    override fun onActive() {
        super.onActive()
        compositeDisposable.add(
        Flowable.interval(0, 1, TimeUnit.SECONDS)
            .subscribe {
                makeRequest(baseCurrency)
            })

    }

    fun updateBaseCurrency(baseCurrency: String){
        this.baseCurrency = baseCurrency
        compositeDisposable.add(makeRequest(baseCurrency))
    }

    fun makeRequest(baseCurrency: String): Disposable{
        return api.getLatesRates(baseCurrency)
            .subscribeOn(Schedulers.io())
            .map{
                it.rates.map { Currency(it.key, it.value) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                value = it
            },{

            })


    }

    override fun onInactive() {
        super.onInactive()
        compositeDisposable.clear()
    }

}