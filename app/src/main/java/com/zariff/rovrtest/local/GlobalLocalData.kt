package com.zariff.rovrtest.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "global_table")

data class GlobalLocalData(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val totalCases:Int,
    val totalRecovered:Int,
    val totalDeath:Int
)