package com.jetlaunch.revoluttest.network

import com.jetlaunch.revoluttest.models.CurrencyResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("latest")
    fun getLatesRates(@Query("base") baseCurrency: String): Observable<CurrencyResponse>

    companion object{
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}