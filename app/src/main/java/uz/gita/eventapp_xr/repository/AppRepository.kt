package uz.gita.eventapp_xr.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.eventapp_xr.data.room.entity.EventEntity


interface AppRepository {

    suspend fun insertEventData(eventEntity: EventEntity)

    suspend fun updateEventData(eventEntity: EventEntity)

    fun getAllEvents(): Flow<List<EventEntity>>
}