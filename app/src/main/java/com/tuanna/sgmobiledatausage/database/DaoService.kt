package com.tuanna.sgmobiledatausage.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity

@Dao
interface DaoService {

    @Query("SELECT * FROM record")
    fun getAllRecords(): List<QuarterRecordEntity>

    @Query("DELETE FROM record")
    fun deleteAllRecords()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(records: List<QuarterRecordEntity>)
}