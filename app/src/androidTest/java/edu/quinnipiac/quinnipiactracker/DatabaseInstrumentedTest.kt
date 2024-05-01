package edu.quinnipiac.quinnipiactracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import edu.quinnipiac.quinnipiactracker.data.academic.Building
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingDao
import edu.quinnipiac.quinnipiactracker.data.academic.BuildingRoomDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseInstrumentedTest {
    private lateinit var buildingDao: BuildingDao
    private lateinit var buildingRoomDatabase: BuildingRoomDatabase
    private var building1 = Building(1, "Echlin Center", 41.4183663748876, -72.89706751864954)
    private var building2 = Building(2, "Communications, Computing and Engineering", 41.41950915640872, -72.89738883168646)

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        buildingRoomDatabase = Room.inMemoryDatabaseBuilder(context, BuildingRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        buildingDao = buildingRoomDatabase.buildingDao()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.quinnipiac.quinnipiactracker", appContext.packageName)
    }

    private suspend fun addOneItemToDb() {
        buildingDao.insert(building1)
    }

    private suspend fun addTwoItemsToDb() {
        buildingDao.insert(building1)
        buildingDao.insert(building2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItem = buildingDao.getItems().first().sortedBy { it.id }
        Assert.assertEquals(allItem[0], building1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = buildingDao.getItems().first().sortedBy { it.id }
        Assert.assertEquals(allItems[0], building1)
        Assert.assertEquals(allItems[1], building2)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        buildingRoomDatabase.close()
    }
}