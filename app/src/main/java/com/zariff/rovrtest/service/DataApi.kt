package com.zariff.rovrtest.service

import com.zariff.rovrtest.model.CountryData
import com.zariff.rovrtest.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {

    @GET("summary")
    fun getSummary(): Call<DataModel>

    @GET("/country/{countryName}")
    fun getCountryByDate(
            @Path("countryName") countryName: String,
            @Query("from") startDate: String,
            @Query("to") endDate: String
    ): Call<List<CountryData>>

    @GET("countries")
    fun getCountries(): Call<Array<CountryData>>
}