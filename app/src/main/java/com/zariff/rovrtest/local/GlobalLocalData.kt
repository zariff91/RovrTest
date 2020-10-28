package com.zariff.rovrtest.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "global_table")

data class GlobalLocalData(

    val totalCases:Int,
    val totalRecovered:Int,
    val totalDeath:Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}