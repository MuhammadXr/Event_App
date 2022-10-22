package uz.gita.eventapp_xr.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.eventapp_xr.directions.AboutDirections
import uz.gita.eventapp_xr.directions.MainDirections
import uz.gita.eventapp_xr.directions.SplashDirections
import uz.gita.eventapp_xr.directions.impl.AboutDirectionsImpl
import uz.gita.eventapp_xr.directions.impl.MainDirectionsImpl
import uz.gita.eventapp_xr.directions.impl.SplashDirectionsImpl


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindIntroScreenDirections(impl: MainDirectionsImpl): MainDirections

    @Binds
    fun bindsSplashScreen(impl:SplashDirectionsImpl):SplashDirections

    @Binds
    fun bindsAboutScreenDirections( impl: AboutDirectionsImpl): AboutDirections

}