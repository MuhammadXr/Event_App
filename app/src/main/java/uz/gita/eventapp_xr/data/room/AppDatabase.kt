package uz.gita.eventapp_xr.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.eventapp_xr.data.room.dao.EventDao
import uz.gita.eventapp_xr.data.room.entity.EventEntity


@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {



    abstract fun eventDao(): EventDao
}