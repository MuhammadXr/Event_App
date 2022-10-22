package uz.gita.eventapp_xr.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Dialog
import kiwi.orbit.compose.ui.controls.IconButton
import kiwi.orbit.compose.ui.controls.Text
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.eventapp_xr.MainActivity
import uz.gita.eventapp_xr.data.room.entity.EventEntity
import uz.gita.eventapp_xr.utils.Constants
import uz.gita.eventapp_xr.utils.Utils
import uz.gita.eventapp_xr.viewmodel.MainIntent
import uz.gita.eventapp_xr.viewmodel.MainUiState
import uz.gita.eventapp_xr.viewmodel.impl.MainViewModelImpl


class MainScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModelImpl>()
        val uiState = viewModel.collectAsState().value



        MainScreenContent(uiState, viewModel::onEventDispatcher)

    }

}



@Composable
fun MainScreenContent(
    uiState: MainUiState,
    eventDispatcher: (MainIntent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrbitTheme.colors.surface.main)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(OrbitTheme.colors.primary.strong),

        ){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Events List",
                style = OrbitTheme.typography.title2,
                color = Color.White
            )
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Spacer(modifier = Modifier.weight(1f))




                Column {
                    IconButton(onClick = { eventDispatcher(MainIntent.DialogStateChange)}) {
                        Image(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }

                if (uiState.dialogExpanse) {
                    MoreDialog(eventDispatcher)
                }
        }

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(
                count = uiState.lists.size
            ) { index ->
                val data = uiState.lists[index]
                EventItem(
                    eventEntity = data
                ) { eventDispatcher(MainIntent.ButtonSwitch(data)) }

            }
        }


    }
}

@Composable
fun EventItem(
    eventEntity: EventEntity,
    onClick: () -> Unit
) {

    val check = remember { mutableStateOf(eventEntity.status == 1) }
    Row(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Constants.images[eventEntity.id - 1], contentDescription = "Image",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(50.dp)
                .background(color = OrbitTheme.colors.primary.subtleAlt, RoundedCornerShape(50))
                .padding(10.dp),
            colorFilter = ColorFilter.tint(
                if (check.value)
                    OrbitTheme.colors.success.normal
                else
                    Color.Black
            )
        )
        Text(text = eventEntity.name, style = OrbitTheme.typography.title3)
        Spacer(modifier = Modifier.weight(1f))


        androidx.compose.material3.Switch(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .background(Color.Transparent),
            checked = check.value,
            onCheckedChange = {
                check.value = it
                eventEntity.status = if (it) 0 else 1
                onClick.invoke()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = OrbitTheme.colors.primary.subtle,
                checkedTrackColor = OrbitTheme.colors.primary.normalAlt,
                uncheckedThumbColor = OrbitTheme.colors.surface.disabled,
                uncheckedTrackColor = OrbitTheme.colors.surface.main,
            )
        )


    }
}


@Composable
fun MoreDialog(
    eventDispatcher: (MainIntent) -> Unit
) {

    val activity = LocalContext.current as? Activity
    val context = LocalView.current.context

    Dialog(
        onDismissRequest = { eventDispatcher(MainIntent.DialogStateChange) },
        title = { Text(text = "Menu") },
        text = {

        },
        properties = DialogProperties(),
        buttons = {
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        eventDispatcher(MainIntent.AboutButton)
                        eventDispatcher(MainIntent.DialogStateChange)
                               },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "about",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(40.dp)
                        .background(
                            color = OrbitTheme.colors.primary.subtleAlt,
                            RoundedCornerShape(50)
                        )
                        .padding(8.dp),
                    colorFilter = ColorFilter.tint(OrbitTheme.colors.primary.normal),
                    alignment = Alignment.Center
                )
                Text(text = "About", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 24.dp))

            }


            Row(
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        Utils.goToPlayMarket(activity as MainActivity)
                        eventDispatcher(MainIntent.DialogStateChange)
                               },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    imageVector = Icons.Filled.RateReview,
                    contentDescription = "about",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(40.dp)
                        .background(
                            color = OrbitTheme.colors.primary.subtleAlt,
                            RoundedCornerShape(50)
                        )
                        .padding(8.dp),
                    colorFilter = ColorFilter.tint(OrbitTheme.colors.primary.normal),
                    alignment = Alignment.Center
                )

                Text(text = "Rate App", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 24.dp))

            }

            Row(
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        eventDispatcher(MainIntent.DialogStateChange)
                        Utils.share(context)
                               },
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "about",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(40.dp)
                        .background(
                            color = OrbitTheme.colors.primary.subtleAlt,
                            RoundedCornerShape(50)
                        )
                        .padding(8.dp),
                    colorFilter = ColorFilter.tint(OrbitTheme.colors.primary.normal),
                    alignment = Alignment.Center
                )

                Text(text = "Share App", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 24.dp))

            }

        }
    )
}