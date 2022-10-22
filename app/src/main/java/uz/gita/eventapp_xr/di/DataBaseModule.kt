package uz.gita.eventapp_xr.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.eventapp_xr.data.room.AppDatabase
import uz.gita.eventapp_xr.data.room.dao.EventDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context:Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "event_data.db")
            .createFromAsset("event_data.db")
            .build()


    }

    @[Provides Singleton]
    fun provideEventDao(appDatabase: AppDatabase): EventDao =
        appDatabase.eventDao()
}