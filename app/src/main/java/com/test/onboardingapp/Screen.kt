package com.test.onboardingapp

sealed class Screen(val route: String) {
    object FirstScreen: Screen("first_screen")
    object FinalOnboardingScreen: Screen("final_onboarding_screen")
    object PagerScreen: Screen("pager")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}