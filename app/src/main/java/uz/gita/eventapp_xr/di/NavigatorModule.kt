package uz.gita.eventapp_xr.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.eventapp_xr.navigation.AppNavigator
import uz.gita.eventapp_xr.navigation.NavigationDispatcher
import uz.gita.eventapp_xr.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {
    @Binds
    fun appNavigator(dispatcher: NavigationDispatcher): AppNavigator

    @Binds
    fun navigationHandler(dispatcher: NavigationDispatcher): NavigationHandler
}