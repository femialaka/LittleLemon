package com.example.littlelemon.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.navigation.ScreenHomeRoute
import com.example.littlelemon.navigation.ScreenOnboardingRoute
import com.example.littlelemon.navigation.ScreenProfileRoute
import com.example.littlelemon.utils.PreferencesManager

@SuppressLint("UnrememberedMutableState")
@Composable
fun Home(navController: NavController) {

    val sharedPreferences = PreferencesManager(LocalContext.current)
    val loggedIn: MutableState<Boolean> = mutableStateOf(sharedPreferences.getBool("IS_LOGGED_IN", false))

    Column() {
        Text(text = "HOME")

        Button(onClick = {
            navController.navigate(ScreenProfileRoute.route)
        })

        {
            Text("Profile")
        }

        Text("Logged In  ${loggedIn.value}")

        Button(
            onClick = {
                navController.navigate(ScreenProfileRoute.route)
            },
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.primary_002)
            ),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.fillMaxWidth()
        )

        {
            Text(
                "Profile",
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                color = colorResource(id = R.color.highlight_002),
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}