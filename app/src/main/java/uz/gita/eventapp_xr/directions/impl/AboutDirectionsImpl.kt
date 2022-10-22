package uz.gita.eventapp_xr.directions.impl

import uz.gita.eventapp_xr.directions.AboutDirections
import uz.gita.eventapp_xr.navigation.AppNavigator
import javax.inject.Inject

class AboutDirectionsImpl @Inject constructor(val appNavigator: AppNavigator): AboutDirections {
    override suspend fun back() {
        appNavigator.back()
    }
}