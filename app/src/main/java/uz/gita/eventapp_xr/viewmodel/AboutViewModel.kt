package uz.gita.eventapp_xr.viewmodel

import uz.gita.eventapp_xr.utils.AppViewModel

interface AboutViewModel: AppViewModel<AboutIntent, AboutUiState, Nothing> {
}

sealed interface AboutIntent{
    object Back: AboutIntent
}

data class AboutUiState(
    val state: Boolean = false
)