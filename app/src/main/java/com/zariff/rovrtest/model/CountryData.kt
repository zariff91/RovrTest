package com.zariff.rovrtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryData(

        @SerializedName("Country")
        val country: String? = null,

        @SerializedName("NewConfirmed")
        val newConfirmed: Int? = null,

        @SerializedName("TotalConfirmed")
        val totalConfirmed: Int? = null,

        @SerializedName("NewDeaths")
        val newDeaths: Int? = null,

        @SerializedName("NewRecovered")
        val newRecovered: Int? = null,

        @SerializedName("Confirmed")
        val searchConfirm: Int? = null,

        @SerializedName("Deaths")
        val searchDeaths: Int? = null,

        @SerializedName("Recovered")
        val searchRecovered: Int? = null,

        @SerializedName("Active")
        val searchActive: Int? = null

) {
    override fun toString(): String {
        return "CountryData(country=$country, newConfirmed=$newConfirmed, totalConfirmed=$totalConfirmed, newDeaths=$newDeaths, newRecovered=$newRecovered, searchConfirm=$searchConfirm, searchDeaths=$searchDeaths, searchRecovered=$searchRecovered, searchActive=$searchActive)"
    }
}