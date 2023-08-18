package com.example.littlelemon.navigation

interface Destinations {
    val route: String
}

object ScreenHomeRoute : Destinations {  override val route = "Home" }

object ScreenOnboardingRoute : Destinations { override val route = "Onboarding" }

object ScreenProfileRoute : Destinations { override val route = "Profile" }