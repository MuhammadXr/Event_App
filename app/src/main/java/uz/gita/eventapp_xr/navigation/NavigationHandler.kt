package uz.gita.eventapp_xr.navigation


import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.Flow


typealias NavArgs = Navigator.() -> Unit

interface NavigationHandler {

    val navStack: Flow<NavArgs>

}