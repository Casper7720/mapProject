package kz.com.myapplication34.presentation.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kz.com.mtapplication34.databinding.ActivityMainBinding
import kz.com.myapplication34.data.entity.LocationEntity
import ru.dgis.sdk.*
import ru.dgis.sdk.map.*
import ru.dgis.sdk.map.Map

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var sdkContext: Context
    lateinit var mapSource: MyLocationMapObjectSource

    private val viewModel: MainViewModel by viewModels()

    private var map: Map? = null

    private lateinit var mapView: MapView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sdkContext = DGis.initialize(
            applicationContext,
            HttpOptions(),
            LogOptions(LogLevel.INFO, LogLevel.WARNING),
            VendorConfig(),
            KeySource(KeyFromAsset("dgissdk.key"))
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSubscribers()

        binding.mapView.also {
            it.getMapAsync { map ->
                onMapReady(map)
            }
            it.showApiVersionInCopyrightView = true
        }
    }


    private fun initSubscribers(){
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.allLocations.collect{list ->
                    if(list.isNotEmpty()){
                        Toast.makeText(this@MainActivity, "Последняя добавленная локация: ${list.last()}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        map?.close()
    }


    private fun onMapReady(map: Map) {
        this.map = map

        binding.mapView.setTouchEventsObserver(object : TouchEventsObserver {
            override fun onTap(point: ScreenPoint) {
                var tap = true
                val visibleRectChannel = map.camera.visibleRectChannel
                val connection = visibleRectChannel.connect { geoRect ->
                    if (tap) {
                        viewModel.addLocation(
                            locationEntity = LocationEntity(
                                id = System.currentTimeMillis(),
                                southWestPointLatitude = geoRect.southWestPoint.latitude.value,
                                southWestPointLongitude = geoRect.southWestPoint.longitude.value,
                                northEastPointLatitude = geoRect.northEastPoint.latitude.value,
                                northEastPointLongitude = geoRect.northEastPoint.longitude.value
                            )
                        )
                        tap = false
                    }
                }
            }
        })

        mapSource = MyLocationMapObjectSource(
            sdkContext,
            MyLocationDirectionBehaviour.FOLLOW_MAGNETIC_HEADING,
            createSmoothMyLocationController()
        )

        map.addSource(mapSource)
    }
}