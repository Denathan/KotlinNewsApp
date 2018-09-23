package com.denath.kotlinnewsapp.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var myInstance : Retrofit? = null

    val instance : Retrofit
    get() {
        if (myInstance == null) {
            myInstance = Retrofit.Builder()
                    .baseUrl("https://content.guardianapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return myInstance!!
    }
}