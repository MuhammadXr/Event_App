package uz.gita.eventapp_xr.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "event_data")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var status: Int = 0
)
