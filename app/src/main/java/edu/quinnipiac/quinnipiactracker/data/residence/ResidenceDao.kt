/**
 * The ResidenceDao defines the database operations for the Building entity.
 * It uses SQLite syntax for the commands themselves.
 */
package edu.quinnipiac.quinnipiactracker.data.residence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ResidenceDao {
    // Inserting into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Residence)

    // Updating the database
    @Update
    suspend fun update(item: Residence)

    // Deleting from the database
    @Delete
    suspend fun delete(item: Residence)

    // Returns the entities ordered by name in ascending order
    @Query("SELECT * from residences ORDER BY name ASC")
    fun getItems(): Flow<List<Residence>>

    // Returns a single entity with the specified id
    @Query("SELECT * from residences WHERE id = :id")
    fun getItem(id: Int): Flow<Residence>
}