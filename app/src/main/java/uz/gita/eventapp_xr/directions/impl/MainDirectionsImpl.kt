package uz.gita.eventapp_xr.directions.impl

import uz.gita.eventapp_xr.directions.MainDirections
import uz.gita.eventapp_xr.navigation.AppNavigator
import uz.gita.eventapp_xr.ui.AboutScreen
import javax.inject.Inject

class MainDirectionsImpl @Inject constructor(private val navigator: AppNavigator): MainDirections {
    override suspend fun gotoAbout() {
        navigator.navigateTo(AboutScreen())
    }

    override suspend fun exit() {
        navigator.back()
    }
}