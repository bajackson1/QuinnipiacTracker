/**
 * The DiningRoomDatabase is a Room database that stores the Dining entities.
 **/
package edu.quinnipiac.quinnipiactracker.data.dining

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dining::class], version = 1, exportSchema = false)
abstract class DiningRoomDatabase : RoomDatabase() {

    // Returns an instance of the DAO interface
    abstract fun diningDao(): DiningDao

    companion object {
        @Volatile
        private var INSTANCE: DiningRoomDatabase? = null

        // Returns an instance of the database and creates a new instance if none exist
        fun getDatabase(context: Context): DiningRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiningRoomDatabase::class.java,
                    // Database name
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