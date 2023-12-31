package kz.com.myapplication34.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.com.myapplication34.data.entity.LocationEntity
import kz.com.myapplication34.data.dao.LocationDao

@Database(
    entities = [LocationEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao
}