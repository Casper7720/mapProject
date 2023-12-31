package kz.com.myapplication34.domain.useCases.locationUseCases

import kotlinx.coroutines.flow.Flow
import kz.com.myapplication34.data.Entity.LocationEntity
import kz.com.myapplication34.data.repository.locationRepository.LocationRepository
import javax.inject.Inject

class AddLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(locationEntity: LocationEntity){
        locationRepository.addLocation(locationEntity)
    }
}