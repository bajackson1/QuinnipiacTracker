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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Building)

    @Update
    suspend fun update(item: Building)

    @Delete
    suspend fun delete(item: Building)

    @Query("SELECT * from academic ORDER BY name ASC")
    fun getItems(): Flow<List<Building>>

    @Query("SELECT * from academic WHERE id = :id")
    fun getItem(id: Int): Flow<Building>
}