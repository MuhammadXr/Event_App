package uz.gita.eventapp_xr.navigation

import android.util.Log
import cafe.adriel.voyager.androidx.AndroidScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : AppNavigator, NavigationHandler {
    override val navStack = MutableSharedFlow<NavArgs>()

    private suspend fun navigate(arg: NavArgs) {
        navStack.emit(arg)
        Log.d("TTT", "NAVSTACK  $navStack")
    }

    override suspend fun back() = navigate {
        pop()
    }

    override suspend fun backAll() = navigate {
        popAll()
    }

    override suspend fun backToRoot() = navigate {
        popUntilRoot()
    }

    override suspend fun navigateTo(screen: AndroidScreen) = navigate {
        push(screen)

    }

    override suspend fun splashNavigateTo(screen: AndroidScreen) = navigate {
        replace(screen)

    }
}