package com.test.onboardingapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.onboardingapp.screens.FinalOnboardingScreen

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PagerScreen.route) {
    composable(route = Screen.PagerScreen.route) {
        Pager(navController = navController, context = context)
    }
    composable(route = Screen.FinalOnboardingScreen.route) {
        FinalOnboardingScreen()
    }}
}