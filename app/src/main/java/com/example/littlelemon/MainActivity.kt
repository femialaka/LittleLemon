package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.littlelemon.navigation.NavigationComposable

class MainActivity : ComponentActivity() {


   // https://www.coursera.org/learn/android-app-capstone/supplement/3qZX5/exercise-fetching-and-storing-the-food-menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          NavigationComposable()
        }
    }

    @Composable
    fun Hello() {
        Text(text = "hello")
    }

    override fun onStart() {
        super.onStart()

        val isLoggedIn = "IS_LOGGED_IN"
        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        val lastLogin = sharedPreferences.getBoolean(isLoggedIn, false)
        Log.i(isLoggedIn, "New count: $lastLogin")
        sharedPreferences.edit().putBoolean(isLoggedIn, lastLogin).apply()

    }
}