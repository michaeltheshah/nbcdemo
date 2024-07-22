package com.example.nbcdemo.ui

import androidx.compose.material3.Scaffold
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nbcdemo.MainActivity
import com.example.nbcdemo.data.repository.FakeShelfRepository
import com.example.nbcdemo.ui.screens.HomeScreen
import com.example.nbcdemo.ui.theme.NBCAppTheme
import com.example.nbcdemo.ui.viewmodel.HomeViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun homeScreenDisplaysShelves() {
        composeTestRule.setContent {
            NBCAppTheme {
                Scaffold { contentPadding ->
                    HomeScreen(contentPadding, viewModel = HomeViewModel(FakeShelfRepository()))
                }
            }
        }

        composeTestRule.onNodeWithText("CONTINUE_WATCHING").assertIsDisplayed()
        composeTestRule.onNodeWithText("SHOWS").assertIsDisplayed()
        composeTestRule.onNodeWithText("EPISODES").assertIsDisplayed()
    }
}