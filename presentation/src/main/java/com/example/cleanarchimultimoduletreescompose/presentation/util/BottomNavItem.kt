package com.example.cleanarchimultimoduletreescompose.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import com.example.cleanarchimultimoduletreescompose.R
import com.example.cleanarchimultimoduletreescompose.presentation.destinations.CoilImageScreenDestination
import com.example.cleanarchimultimoduletreescompose.presentation.destinations.Destination
import com.example.cleanarchimultimoduletreescompose.presentation.destinations.TreesListScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
    @ExperimentalMaterialApi
    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = TreesListScreenDestination
    )
    object Other: BottomNavItem(
        title = "Other",
        icon = R.drawable.ic_bookmark,
        destination = CoilImageScreenDestination
    )
}
