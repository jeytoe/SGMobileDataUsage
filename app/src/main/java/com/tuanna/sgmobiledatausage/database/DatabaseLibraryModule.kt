package com.tuanna.sgmobiledatausage.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseLibraryModule(context: Context) {
    private val database: Database

    init {
        database = Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesDatabaseInstance(): Database {
        return database
    }

    @Provides
    fun providesDaoService(database: Database): DaoService {
        return database.daoService()
    }

    @Singleton
    @Provides
    fun providesRecordRepository(daoService: DaoService): RecordRepository {
        return RecordRepository(daoService)
    }

    companion object {

        private const val DATABASE_NAME = "records_db"
    }
}