package com.zariff.rovrtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GlobalData {

    @SerializedName("TotalConfirmed")
    @Expose
    val totalConfirmed: Int? = null

    @SerializedName("TotalDeaths")
    @Expose
    val totalDeaths: Int? = null

    @SerializedName("TotalRecovered")
    @Expose
    val totalRecovered: Int? = null

}