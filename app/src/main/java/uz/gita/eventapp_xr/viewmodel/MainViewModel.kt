package uz.gita.eventapp_xr.viewmodel

import uz.gita.eventapp_xr.data.room.entity.EventEntity
import uz.gita.eventapp_xr.utils.AppViewModel


interface MainViewModel: AppViewModel<MainIntent, MainUiState, Nothing> {

}

sealed interface MainIntent{
    object AboutButton: MainIntent

    class ButtonSwitch(val data: EventEntity): MainIntent

    object DialogStateChange: MainIntent
}

data class MainUiState(
    val lists: List<EventEntity> = emptyList(),
    var dialogExpanse: Boolean = false
)
