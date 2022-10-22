package uz.gita.eventapp_xr.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.eventapp_xr.data.room.entity.EventEntity
import uz.gita.eventapp_xr.directions.MainDirections
import uz.gita.eventapp_xr.repository.AppRepository
import uz.gita.eventapp_xr.viewmodel.MainIntent
import uz.gita.eventapp_xr.viewmodel.MainUiState
import uz.gita.eventapp_xr.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(val repository: AppRepository, val mainDirections: MainDirections) : MainViewModel, ViewModel() {

    val uiState = MainUiState()

    override val container: Container<MainUiState, Nothing> = container(uiState){
        getAllData()
    }

    override fun onEventDispatcher(intent: MainIntent) = intent{
        when(intent){
            is MainIntent.AboutButton -> {
                mainDirections.gotoAbout()
            }

            is MainIntent.ButtonSwitch -> {
                updateData(intent.data)
            }

            is MainIntent.DialogStateChange -> {
                reduce { state.copy(dialogExpanse = !state.dialogExpanse) }
            }
        }
    }

    private fun getAllData() = intent{

        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllEvents().collectLatest {
                reduce {
                    state.copy(lists = it)
                }
            }

        }


    }

    private fun updateData(data: EventEntity){
        viewModelScope.launch (Dispatchers.IO) {
            data.status = if(data.status == 1) 0 else 1
            repository.updateEventData(data)
        }
    }


}