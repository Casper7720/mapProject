package kz.com.myapplication34

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.dgis.sdk.Context
import ru.dgis.sdk.DGis

@HiltAndroidApp
class App: Application() {
    lateinit var sdkContext: Context

    override fun onCreate() {
        super.onCreate()

        sdkContext = DGis.initialize(this)
    }

}