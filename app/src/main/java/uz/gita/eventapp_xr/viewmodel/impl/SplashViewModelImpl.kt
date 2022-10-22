package uz.gita.eventapp_xr.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.eventapp_xr.directions.SplashDirections
import uz.gita.eventapp_xr.viewmodel.SplashIntent
import uz.gita.eventapp_xr.viewmodel.SplashUiState
import uz.gita.eventapp_xr.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val directions: SplashDirections): SplashViewModel, ViewModel() {
    override fun onEventDispatcher(intent: SplashIntent) {
        when(intent){
            is SplashIntent.GotoMain -> {
                viewModelScope.launch {
                    directions.gotoMain()

                }
            }
        }
    }

    private val uiState = SplashUiState()
    override val container: Container<SplashUiState, Nothing> = container(uiState)

    init {
        viewModelScope.launch {
            delay(1500)
            directions.gotoMain()

        }
    }
}