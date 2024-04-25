package edu.quinnipiac.quinnipiactracker.data.dining

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dining::class], version = 1, exportSchema = false)
abstract class DiningRoomDatabase : RoomDatabase() {

    abstract fun diningDao(): DiningDao

    companion object {
        @Volatile
        private var INSTANCE: DiningRoomDatabase? = null

        fun getDatabase(context: Context): DiningRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiningRoomDatabase::class.java,
                    "dining_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}