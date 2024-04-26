/**
 * This file is a default data class for the academic building database.
 * It stores the name of the building itself and the coordinates for the map.
 */
package edu.quinnipiac.quinnipiactracker.data.residence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Residence.kt
@Entity(tableName = "residences")
data class Residence(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double
)