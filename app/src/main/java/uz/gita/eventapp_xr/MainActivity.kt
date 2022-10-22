package uz.gita.eventapp_xr

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kiwi.orbit.compose.ui.OrbitTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.eventapp_xr.navigation.NavigationHandler
import uz.gita.eventapp_xr.service.EventService
import uz.gita.eventapp_xr.ui.SplashScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(applicationContext, EventService::class.java)
        applicationContext.startService(intent)

        Log.d("TTT", "NAVSTACK  ${navigationHandler.navStack}")

        setContent {
            OrbitTheme {
                val uiController = rememberSystemUiController()
                uiController.setStatusBarColor(
                    color = OrbitTheme.colors.primary.subtleAlt
                )


                Navigator(screen = SplashScreen()) { navigator ->
                    navigationHandler.navStack
                        .onEach { it.invoke(navigator) }
                        .launchIn(lifecycleScope)
                    SlideTransition(navigator = navigator)
                }

            }
        }
    }


}

