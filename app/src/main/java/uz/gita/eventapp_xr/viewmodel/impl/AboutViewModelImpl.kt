package uz.gita.eventapp_xr.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.eventapp_xr.directions.AboutDirections
import uz.gita.eventapp_xr.viewmodel.AboutIntent
import uz.gita.eventapp_xr.viewmodel.AboutUiState
import uz.gita.eventapp_xr.viewmodel.AboutViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModelImpl @Inject constructor(val directions: AboutDirections) : AboutViewModel, ViewModel() {
    override fun onEventDispatcher(intent: AboutIntent) = intent{
        when(intent){
            is AboutIntent.Back -> directions.back()
        }
    }

    override val container: Container<AboutUiState, Nothing> = container(AboutUiState())
}