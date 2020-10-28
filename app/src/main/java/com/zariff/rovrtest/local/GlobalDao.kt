package com.zariff.rovrtest.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GlobalDao {

    @Insert
    fun addGlobalData(global:GlobalLocalData)

    @Query("SELECT *FROM global_table")
    fun readAllData():GlobalLocalData

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGlobal(global: GlobalLocalData):Int

}