/**
* The BuildingRoomDatabase is a Room database that stores the Building entities.
**/
package edu.quinnipiac.quinnipiactracker.data.academic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Building::class], version = 1, exportSchema = false)
abstract class BuildingRoomDatabase : RoomDatabase() {

    // Returns an instance of the DAO interface
    abstract fun buildingDao(): BuildingDao

    companion object {
        @Volatile
        private var INSTANCE: BuildingRoomDatabase? = null

        // Returns an instance of the database and creates a new instance if none exist
        fun getDatabase(context: Context): BuildingRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BuildingRoomDatabase::class.java,
                    // Database name
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