package kz.com.myapplication34.data.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteOpenHelper
import kz.com.myapplication34.data.Entity.LocationEntity
import kz.com.myapplication34.data.dao.LocationDao

@Database(
    entities = [LocationEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao
}