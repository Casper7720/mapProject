package kz.com.myapplication34.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.com.myapplication34.data.Entity.LocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(locationEntity: LocationEntity)

    @Query("SELECT * FROM ${LocationEntity.TABLE_NAME}")
    fun getAll(): List<LocationEntity>
}