package uz.gita.eventapp_xr.ui

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Text
import uz.gita.eventapp_xr.R
import uz.gita.eventapp_xr.utils.ClearRippleTheme
import uz.gita.eventapp_xr.viewmodel.SplashIntent
import uz.gita.eventapp_xr.viewmodel.SplashViewModel
import uz.gita.eventapp_xr.viewmodel.impl.SplashViewModelImpl


class SplashScreen: AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: SplashViewModel = getViewModel<SplashViewModelImpl>()
        SplashScreenContent(eventDispatcher = viewModel::onEventDispatcher)
    }
}

@Composable
fun SplashScreenContent(
    eventDispatcher: (SplashIntent) -> Unit
)
{
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OrbitTheme.colors.surface.background)
                .clickable {
                    eventDispatcher(SplashIntent.GotoMain)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.event_icon),
                contentDescription = "Splash Image"
            )
            Text(
                text = "Event App",
                style = OrbitTheme.typography.title1,
                color = OrbitTheme.colors.primary.normal,
                modifier = Modifier.padding(12.dp)
            )

        }
        Box(modifier = Modifier.fillMaxSize()) {
            val pm: PackageManager =
                LocalView.current.context.applicationContext.packageManager
            val pkgName: String = LocalView.current.context.applicationContext.packageName
            var pkgInfo: PackageInfo? = null
            try {
                pkgInfo = pm.getPackageInfo(pkgName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            val ver = pkgInfo!!.versionName

            Text(
                text = "Version $ver",
                style = OrbitTheme.typography.title5,
                color = OrbitTheme.colors.primary.normal,
                modifier = Modifier.padding(12.dp).align(Alignment.BottomCenter)
            )
        }
    }
}