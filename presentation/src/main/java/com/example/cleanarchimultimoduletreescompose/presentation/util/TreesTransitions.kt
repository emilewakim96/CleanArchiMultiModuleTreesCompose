package com.example.cleanarchimultimoduletreescompose.presentation.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavBackStackEntry
import com.example.cleanarchimultimoduletreescompose.presentation.destinations.TreesListScreenDestination
import com.example.cleanarchimultimoduletreescompose.presentation.navDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
object TreesTransitions : DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return when (initialState.navDestination) {
            TreesListScreenDestination -> {
                slideInHorizontally(
                    initialOffsetX = { 500 },
                    animationSpec = tween(300)
                )
            }
            else -> null
        }
    }

//    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
//        return when (targetState.navDestination) {
//            TreesListScreenDestination ->
//                slideOutHorizontally(
//                    targetOffsetX = { -700 },
//                    animationSpec = tween(500)
//                )
//            else -> null
//        }
//    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition? {
        return when (initialState.navDestination) {
            TreesListScreenDestination ->
                slideInHorizontally(
                    initialOffsetX = { -700 },
                    animationSpec = tween(500)
                )
            else -> null
        }
    }

//    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition? {
//        return when (targetState.navDestination) {
//            TreesListScreenDestination ->
//                slideOutHorizontally(
//                    targetOffsetX = { 700 },
//                    animationSpec = tween(500)
//                )
//            else -> null
//        }
//    }

}