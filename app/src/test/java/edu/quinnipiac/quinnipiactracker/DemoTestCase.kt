package edu.quinnipiac.quinnipiactracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.quinnipiac.quinnipiactracker.data.Building
import edu.quinnipiac.quinnipiactracker.data.BuildingDao
import edu.quinnipiac.quinnipiactracker.data.BuildingRoomDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BuildingDaoTest {

    private lateinit var buildingDao: BuildingDao
    private lateinit var db: BuildingRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BuildingRoomDatabase::class.java
        ).build()
        buildingDao = db.buildingDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAllBuildings() = runBlocking {
        val building = Building(1, "Building A", 41.2739, -72.9673)
        buildingDao.insert(building)
        val allBuildings = buildingDao.getItems().first()
        assertThat(allBuildings.size, `is`(1))
        assertThat(allBuildings[0], `is`(building))
    }
}
