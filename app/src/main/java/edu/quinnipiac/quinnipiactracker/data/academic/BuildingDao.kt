/**
 * The BuildingDao defines the database operations for the Building entity.
 * It uses SQLite syntax for the commands themselves.
 */
package edu.quinnipiac.quinnipiactracker.data.academic

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingDao {
    // Inserting into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Building)

    // Updating the database
    @Update
    suspend fun update(item: Building)

    // Deleting from the database
    @Delete
    suspend fun delete(item: Building)

    // Returns the entities ordered by name in ascending order
    @Query("SELECT * from academic ORDER BY name ASC")
    fun getItems(): Flow<List<Building>>

    // Returns a single entity with the specified id
    @Query("SELECT * from academic WHERE id = :id")
    fun getItem(id: Int): Flow<Building>
}