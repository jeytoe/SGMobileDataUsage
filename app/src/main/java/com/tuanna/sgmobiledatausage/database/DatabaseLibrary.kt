package com.tuanna.sgmobiledatausage.database

import android.content.Context

class DatabaseLibrary(context: Context) {

    private val libraryComponent: DatabaseLibraryComponent = DaggerDatabaseLibraryComponent.builder()
        .databaseLibraryModule(DatabaseLibraryModule(context))
        .build()

    fun recordRepository(): RecordRepository{
        return libraryComponent.recordRepository()
    }
}