package uz.gita.eventapp_xr.navigation

import cafe.adriel.voyager.androidx.AndroidScreen


typealias AppScreen = AndroidScreen

interface AppNavigator {

    suspend fun back()

    suspend fun backAll()

    suspend fun backToRoot()

    suspend fun navigateTo(screen: AppScreen)

    suspend fun splashNavigateTo(screen: AppScreen)
}