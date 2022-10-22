package uz.gita.eventapp_xr.directions.impl

import uz.gita.eventapp_xr.directions.SplashDirections
import uz.gita.eventapp_xr.navigation.AppNavigator
import uz.gita.eventapp_xr.ui.MainScreen
import javax.inject.Inject

class SplashDirectionsImpl @Inject constructor(private val navigator: AppNavigator) : SplashDirections {
    override suspend fun gotoMain() {

        navigator.splashNavigateTo(MainScreen())
    }
}