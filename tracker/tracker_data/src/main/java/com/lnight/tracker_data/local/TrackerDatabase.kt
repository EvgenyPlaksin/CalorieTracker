package com.lnight.tracker_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lnight.tracker_data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase: RoomDatabase() {

    abstract val dao: TrackerDao

    companion object {
       const val DATABASE_NAME = "tracker_db"
    }

}