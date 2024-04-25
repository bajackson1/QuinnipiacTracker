package edu.quinnipiac.quinnipiactracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ResidenceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Residence)

    @Update
    suspend fun update(item: Residence)

    @Delete
    suspend fun delete(item: Residence)

    @Query("SELECT * from residences ORDER BY name ASC")
    fun getItems(): Flow<List<Residence>>

    @Query("SELECT * from residences WHERE id = :id")
    fun getItem(id: Int): Flow<Residence>
}