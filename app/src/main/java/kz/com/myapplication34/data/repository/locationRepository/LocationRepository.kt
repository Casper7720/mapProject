package kz.com.myapplication34.data.repository.locationRepository

import kz.com.myapplication34.data.Entity.LocationEntity

interface LocationRepository {

    fun addLocation(locationEntity: LocationEntity)

    fun getAllLocations(): List<LocationEntity>
}