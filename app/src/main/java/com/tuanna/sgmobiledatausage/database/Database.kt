package com.tuanna.sgmobiledatausage.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity


@Database(
    entities = [QuarterRecordEntity::class],
    version = com.tuanna.sgmobiledatausage.database.Database.VERSION,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun daoService(): DaoService

    companion object {

        internal const val VERSION = 1
    }
}