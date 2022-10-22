package uz.gita.eventapp_xr.data.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.eventapp_xr.data.room.entity.EventEntity


@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(eventEntity: EventEntity)

    @Update
    suspend fun updateEvent(eventEntity: EventEntity)

    @Query("SELECT*FROM event_data")
    fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT*FROM event_data WHERE id=:id")
    suspend fun getEventById(id: Int): EventEntity
}