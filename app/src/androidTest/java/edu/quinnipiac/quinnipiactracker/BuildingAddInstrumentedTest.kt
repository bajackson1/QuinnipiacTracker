package edu.quinnipiac.quinnipiactracker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BuildingAddInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testBuildingAdd() {
        // Wait for the map to load
        Thread.sleep(5000)

        // Click on the building_group button to go to the BuildingHomeFragment
        onView(withId(R.id.building_group)).perform(click())

        // Wait for the BuildingHomeFragment to load
        Thread.sleep(2000)

        // Click on the add building button to go to the BuildingAddFragment
        onView(withId(R.id.addBuildingFab)).perform(click())

        // Wait for the BuildingAddFragment to load
        Thread.sleep(2000)

        // Input "Tator Hall" into the text field
        onView(withId(R.id.addBuildingTitle)).perform(replaceText("Tator Hall"), closeSoftKeyboard())

        // Press the save button to go back to the BuildingHomeFragment
        onView(withId(R.id.saveBuildingName)).perform(click())

        // Wait for 1 second
        Thread.sleep(1000)

        // Verify that the "Tator Hall" item is added
        onView(withText("Tator Hall")).check(matches(isDisplayed()))

        // Press the back button to go back to the HomeFragment
        onView(withId(R.id.backButton)).perform(click())

        // Wait for the map to load
        Thread.sleep(5000)

        // Click on the building_group button to go to the DiningHomeFragment
        onView(withId(R.id.dining_group)).perform(click())

        // Wait for the BuildingHomeFragment to load
        Thread.sleep(2000)

        // Click on the add dining button to go to the DiningAddFragment
        onView(withId(R.id.addDiningFab)).perform(click())

        // Wait for the DiningAddFragment to load
        Thread.sleep(2000)

        // Input "Bobcat Den/The Rat" into the text field
        onView(withId(R.id.addDiningTitle)).perform(replaceText("Bobcat Den/The Rat"), closeSoftKeyboard())

        // Press the save button to go back to the DiningHomeFragment
        onView(withId(R.id.saveDiningName)).perform(click())

        // Wait for 1 second
        Thread.sleep(1000)

        // Verify that the "Bobcat Den/The Rat" item is added
        onView(withText("Bobcat Den/The Rat")).check(matches(isDisplayed()))

        // Press the back button to go back to the HomeFragment
        onView(withId(R.id.backButton)).perform(click())

        Thread.sleep(5000)

        // Click on the building_group button to go to the ResidenceHomeFragment
        onView(withId(R.id.residence_group)).perform(click())

        // Wait for the ResidenceHomeFragment to load
        Thread.sleep(2000)

        // Click on the add residence button to go to the ResidenceAddFragment
        onView(withId(R.id.addResidenceFab)).perform(click())

        // Wait for the ResidenceAddFragment to load
        Thread.sleep(2000)

        // Input "Village - 400" into the text field
        onView(withId(R.id.addResidenceTitle)).perform(replaceText("Village - 400"), closeSoftKeyboard())

        // Press the save button to go back to the ResidenceHomeFragment
        onView(withId(R.id.saveResidenceName)).perform(click())

        // Wait for 1 second
        Thread.sleep(1000)

        // Verify that the "Village - 400" item is added
        onView(withText("Village - 400")).check(matches(isDisplayed()))

        // Press the back button to go back to the HomeFragment
        onView(withId(R.id.backButton)).perform(click())
    }
}
