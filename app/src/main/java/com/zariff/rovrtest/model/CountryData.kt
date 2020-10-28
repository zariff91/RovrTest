package com.zariff.rovrtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryData {

    @SerializedName("Country")
    @Expose
    val country: String? = null

    @SerializedName("NewConfirmed")
    @Expose
    val newConfirmed: Int? = null

    @SerializedName("TotalConfirmed")
    @Expose
    val totalConfirmed: Int? = null

    @SerializedName("NewDeaths")
    @Expose
    val newDeaths: Int? = null

    @SerializedName("NewRecovered")
    @Expose
    val newRecovered: Int? = null

}