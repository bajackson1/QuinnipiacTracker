/**
 * The DiningDao defines the database operations for the Building entity.
 * It uses SQLite syntax for the commands themselves.
 */
package edu.quinnipiac.quinnipiactracker.data.dining

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DiningDao {
    // Inserting into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Dining)

    // Updating the database
    @Update
    suspend fun update(item: Dining)

    // Deleting from the database
    @Delete
    suspend fun delete(item: Dining)

    // Returns the entities ordered by name in ascending order
    @Query("SELECT * from dinings ORDER BY name ASC")
    fun getItems(): Flow<List<Dining>>

    // Returns a single entity with the specified id
    @Query("SELECT * from dinings WHERE id = :id")
    fun getItem(id: Int): Flow<Dining>
}