package uz.gita.eventapp_xr.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kiwi.orbit.compose.ui.OrbitTheme
import uz.gita.eventapp_xr.R
import uz.gita.eventapp_xr.viewmodel.AboutIntent
import uz.gita.eventapp_xr.viewmodel.AboutViewModel
import uz.gita.eventapp_xr.viewmodel.impl.AboutViewModelImpl

class AboutScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val viewModel:AboutViewModel = getViewModel<AboutViewModelImpl>()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)
                .background(OrbitTheme.colors.surface.background)
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.event_icon),
                contentDescription = "Splash Image"
            )
            kiwi.orbit.compose.ui.controls.Text(
                text = "Event App",
                style = OrbitTheme.typography.title1,
                color = OrbitTheme.colors.primary.normal,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                text = "Version 1.0",
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 12.dp),
                style = OrbitTheme.typography.title4,
            )
            Text(
                text = "This app can listen system events, and can alert you about this",
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),
                style = OrbitTheme.typography.title5,
                textAlign = TextAlign.Center
            )


        }

        Box(modifier = Modifier.fillMaxSize()) {

            IconButton(
                modifier = Modifier.align(Alignment.TopStart).padding(10.dp),
                onClick = { viewModel.onEventDispatcher(AboutIntent.Back)
                }) {
                Image(imageVector = Icons.Filled.Close, contentDescription = "Close")
            }

            Text(
                text = "programmed by Ro\'zi Muhammad",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                style = OrbitTheme.typography.title5,
            )
        }
    }
}