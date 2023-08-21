package com.example.littlelemon.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.MenuItem
import com.example.littlelemon.screens.Home
import com.example.littlelemon.screens.Onboarding
import com.example.littlelemon.screens.Profile
import com.example.littlelemon.utils.PreferencesManager

@SuppressLint("UnrememberedMutableState")
@Composable
fun NavigationComposable(items: List<MenuItem>) {
    val navController = rememberNavController()
    val sharedPreferences = PreferencesManager(LocalContext.current)
    val loggedIn: MutableState<Boolean> = mutableStateOf(sharedPreferences.getBool("IS_LOGGED_IN", false))

    NavHost(navController = navController, startDestination = ScreenOnboardingRoute.route) {
        composable(ScreenHomeRoute.route) {
            Home(navController, items)
        }
        composable(ScreenOnboardingRoute.route) {
            if(loggedIn.value) {
                Log.i("MSG", "LOGGED IN ===> HOME")
                Home(navController, items)
            }
            else {
                Log.i("MSG", "LOGGED IN ===> ONBOARDING")
                Onboarding(navController)
            }
        }
        composable(ScreenProfileRoute.route) {
            Profile(navController)
        }
    }
}