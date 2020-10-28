package com.zariff.rovrtest.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "covid_data")
class DataModel {
    @SerializedName("Global")
    @Expose
    val global: GlobalData? = null

    @SerializedName("Countries")
    @Expose
    val countries: List<CountryData>? = null
}