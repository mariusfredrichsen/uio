package no.uio.ifi.in2000.mafredri.sixpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomBarItem("home", Icons.Rounded.Home, "Home")
    object Workouts : BottomBarItem("search", Icons.AutoMirrored.Rounded.List, "Workouts")
    object CreateWorkout : BottomBarItem("profile", Icons.Rounded.Add, "CreateWorkout")
}