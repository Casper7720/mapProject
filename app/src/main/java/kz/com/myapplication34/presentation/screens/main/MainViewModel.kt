package kz.com.myapplication34.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.com.myapplication34.data.entity.LocationEntity
import kz.com.myapplication34.domain.useCases.locationUseCases.AddLocationUseCase
import kz.com.myapplication34.domain.useCases.locationUseCases.GetAllLocationsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addLocationUseCase: @JvmSuppressWildcards AddLocationUseCase,
    private val getAllLocationsUseCase: @JvmSuppressWildcards GetAllLocationsUseCase
) : ViewModel() {

    private val _allLocations = MutableStateFlow<List<LocationEntity>>(mutableListOf())
    val allLocations = _allLocations.asStateFlow()

    fun addLocation(locationEntity: LocationEntity){
        viewModelScope.launch(Dispatchers.IO) {
            addLocationUseCase.invoke(locationEntity)
        }
    }

    private fun getAllLocations(){
        viewModelScope.async(Dispatchers.IO) {
            _allLocations.emit(getAllLocationsUseCase.invoke())
        }

    }

}