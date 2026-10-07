package com.example.artspace

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.junit.Rule
import org.junit.Test

class ArtSpaceUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initialScreen_showsStarryNight() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceScreen()
                }
            }
        }

        // Verify Starry Night is displayed initially
        composeTestRule.onNodeWithText("The Starry Night").assertIsDisplayed()
        composeTestRule.onNodeWithText("Previous").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").assertIsDisplayed()
    }

    @Test
    fun clickingNext_advancesToMonaLisa() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceScreen()
                }
            }
        }

        // Click Next
        composeTestRule.onNodeWithText("Next").performClick()

        // Verify Mona Lisa is displayed
        composeTestRule.onNodeWithText("Mona Lisa").assertIsDisplayed()
    }

    @Test
    fun clickingPrevious_wrapsToLastArtwork() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceScreen()
                }
            }
        }

        // Click Previous from first artwork
        composeTestRule.onNodeWithText("Previous").performClick()

        // Verify Girl with a Pearl Earring is displayed (the last one)
        composeTestRule.onNodeWithText("Girl with a Pearl Earring").assertIsDisplayed()
    }
}
