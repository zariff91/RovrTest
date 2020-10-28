package com.zariff.rovrtest.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClient {

    companion object Factory {
        fun createApi(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://api.covid19api.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}