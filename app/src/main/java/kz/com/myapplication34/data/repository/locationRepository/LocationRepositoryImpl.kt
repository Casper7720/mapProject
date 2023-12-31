package kz.com.myapplication34.data.repository.locationRepository

import kz.com.myapplication34.data.Entity.LocationEntity
import kz.com.myapplication34.data.database.AppDataBase
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor (
    private val appDataBase: AppDataBase
): LocationRepository {

    override fun addLocation(locationEntity: LocationEntity) {
        appDataBase.getLocationDao().insertLocation(locationEntity)
    }

    override fun getAllLocations(): List<LocationEntity> {
        return appDataBase.getLocationDao().getAll()
    }
}