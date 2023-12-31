package kz.com.myapplication34

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.com.myapplication34.data.dao.LocationDao
import kz.com.myapplication34.data.database.AppDataBase
import kz.com.myapplication34.data.repository.locationRepository.LocationRepository
import kz.com.myapplication34.data.repository.locationRepository.LocationRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindLocationRepository(locationRepository: LocationRepositoryImpl): LocationRepository
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideFavoriteChapterDao(appDatabase: AppDataBase): LocationDao =
        appDatabase.getLocationDao()




    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "AppDataBase"
        ).build()
    }


}