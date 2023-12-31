package kz.com.myapplication34.domain.useCases.locationUseCases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.com.myapplication34.data.Entity.LocationEntity
import kz.com.myapplication34.data.repository.locationRepository.LocationRepository
import javax.inject.Inject

class GetAllLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke() : List<LocationEntity> {
        return locationRepository.getAllLocations()
    }
}