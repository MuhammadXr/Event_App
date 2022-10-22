package uz.gita.eventapp_xr.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.eventapp_xr.repository.AppRepository
import uz.gita.eventapp_xr.repository.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

}