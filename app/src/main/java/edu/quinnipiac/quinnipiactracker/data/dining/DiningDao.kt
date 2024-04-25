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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Dining)

    @Update
    suspend fun update(item: Dining)

    @Delete
    suspend fun delete(item: Dining)

    @Query("SELECT * from dinings ORDER BY name ASC")
    fun getItems(): Flow<List<Dining>>

    @Query("SELECT * from dinings WHERE id = :id")
    fun getItem(id: Int): Flow<Dining>
}