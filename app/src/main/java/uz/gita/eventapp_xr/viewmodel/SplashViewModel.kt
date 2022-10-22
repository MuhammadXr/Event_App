package uz.gita.eventapp_xr.viewmodel

import uz.gita.eventapp_xr.utils.AppViewModel

interface SplashViewModel: AppViewModel<SplashIntent, SplashUiState, Nothing> {
}

sealed interface SplashIntent{
    object GotoMain: SplashIntent
}

data class SplashUiState(
    var gotoMain: Boolean = false
)