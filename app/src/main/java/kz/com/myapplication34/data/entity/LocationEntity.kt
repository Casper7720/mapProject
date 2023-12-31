package kz.com.myapplication34.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.com.myapplication34.data.entity.LocationEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "southWestPointLatitude")
    val southWestPointLatitude: Double,

    @ColumnInfo(name = "southWestPointLongitude")
    val southWestPointLongitude: Double,

    @ColumnInfo(name = "northEastPointLatitude")
    val northEastPointLatitude: Double,

    @ColumnInfo(name = "northEastPointLongitude")
    val northEastPointLongitude: Double,
){
    companion object{
        const val TABLE_NAME = "LocationEntity"
    }
}
