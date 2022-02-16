package com.example.cleanarchimultimoduletreescompose.presentation.util

import com.example.cleanarchimultimoduletreescompose.R
import com.example.destinations.CoilImageScreenDestination
import com.example.destinations.Destination
import com.example.destinations.TreesListScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
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
