package com.tuanna.sgmobiledatausage.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "record")
class QuarterRecordEntity(var volume: Double,
                          @PrimaryKey
                          var quarter: String)