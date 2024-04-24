package edu.quinnipiac.quinnipiactracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Building::class], version = 1, exportSchema = false)
abstract class BuildingRoomDatabase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDao

    companion object {
        @Volatile
        private var INSTANCE: BuildingRoomDatabase? = null

        fun getDatabase(context: Context): BuildingRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BuildingRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}