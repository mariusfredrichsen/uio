package com.example.countries

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.countries.ui.countries.CountriesScreen
import com.example.countries.ui.country.CountryScreen



@Composable
fun CountryApp(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "countries"
    ) {
        composable(
            route = "countries"
        ) {
            CountriesScreen(
                navController = navController,
                modifier = modifier)
        }

        composable(
            route = "country/{countryName}",
            arguments = listOf(navArgument("countryName") {
                type = NavType.StringType
                nullable = false
            })
        ) { navBackStackEntry ->
            CountryScreen(
                navController = navController,
                countryName = navBackStackEntry.arguments?.getString("countryName").toString()
            )
        }
    }
}