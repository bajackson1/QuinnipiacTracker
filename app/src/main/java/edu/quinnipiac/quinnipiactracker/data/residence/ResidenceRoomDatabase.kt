/**
 * The ResidenceRoomDatabase is a Room database that stores the Residence entities.
 **/
package edu.quinnipiac.quinnipiactracker.data.residence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Residence::class], version = 1, exportSchema = false)
abstract class ResidenceRoomDatabase : RoomDatabase() {

    // Returns an instance of the DAO interface
    abstract fun residenceDao(): ResidenceDao

    companion object {
        @Volatile
        private var INSTANCE: ResidenceRoomDatabase? = null

        // Returns an instance of the database and creates a new instance if none exist
        fun getDatabase(context: Context): ResidenceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResidenceRoomDatabase::class.java,
                    // Database name
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