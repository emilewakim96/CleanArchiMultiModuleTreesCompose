package com.example.cleanarchimultimoduletreescompose.presentation.treeslist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cleanarchimultimoduletreescompose.BottomNavigation
import com.example.cleanarchimultimoduletreescompose.MainActivity
import com.example.cleanarchimultimoduletreescompose.presentation.NavGraphs
import com.example.cleanarchimultimoduletreescompose.presentation.util.TestConstants.DELETE_TREE_BUTTON_FROM_DETAILS
import com.example.cleanarchimultimoduletreescompose.presentation.util.TestConstants.TREE_RECORD
import com.example.cleanarchimultimoduletreescompose.ui.theme.CleanArchiMultiModuleTreesComposeTheme
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import com.example.data.di.module.DatabaseModule
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class TreeListScreenTest {

    @get:Rule  // order = 0
    val hiltRule = HiltAndroidRule(this)

    @get:Rule // order = 1
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            CleanArchiMultiModuleTreesComposeTheme {
                val navController = rememberAnimatedNavController()
                val navHostEngine = rememberAnimatedNavHostEngine(
                    navHostContentAlignment = Alignment.TopCenter,
                    rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING, //default `rootDefaultAnimations` means no animations
                )
                Scaffold(
                    bottomBar = { BottomNavigation(navController = navController) }
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root, navController = navController, engine = navHostEngine)
                }
            }
        }
    }

    @Test
    fun swipeToDismissFirstRow_deletesFirstRecord() = runBlocking {
        delay(3000)
        composeRule.onNodeWithTag(TREE_RECORD + "0bbdf66d11e71eb3ec4f71b4b9b44caba6fab430").performTouchInput {
            swipeLeft()
            advanceEventTime(2000)
        }.assertDoesNotExist()
    }

    @Test
    fun deleteFirstTreeFromDetailsPage_deletesTree() = runBlocking {
        delay(3000)
        composeRule.onNodeWithTag(TREE_RECORD + "0bbdf66d11e71eb3ec4f71b4b9b44caba6fab430").performClick()
        composeRule.onNodeWithTag(DELETE_TREE_BUTTON_FROM_DETAILS).performTouchInput {
            click()
            advanceEventTime(2000)
        }
        composeRule.onNodeWithTag(TREE_RECORD + "0bbdf66d11e71eb3ec4f71b4b9b44caba6fab430").assertDoesNotExist()
    }
}