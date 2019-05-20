package com.tuanna.sgmobiledatausage.database

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseLibraryModule::class])
interface DatabaseLibraryComponent {

    fun recordRepository(): RecordRepository
}