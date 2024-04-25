package edu.quinnipiac.quinnipiactracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Residence::class], version = 1, exportSchema = false)
abstract class ResidenceRoomDatabase : RoomDatabase() {

    abstract fun residenceDao(): ResidenceDao

    companion object {
        @Volatile
        private var INSTANCE: ResidenceRoomDatabase? = null

        fun getDatabase(context: Context): ResidenceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResidenceRoomDatabase::class.java,
                    "residence_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}