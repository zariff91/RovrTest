package com.zariff.rovrtest.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GlobalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGlobalData(global:GlobalLocalData)

    @Query("SELECT *FROM global_table")
    fun readAllData():GlobalLocalData

}