/**
 * This file is a default data class for the dining hall database.
 * It stores the name of the building itself and the coordinates for the map.
 */
package edu.quinnipiac.quinnipiactracker.data.dining

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Dining.kt
@Entity(tableName = "dinings")
data class Dining(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double
)