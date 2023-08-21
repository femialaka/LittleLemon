package com.example.littlelemon.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.R
import com.example.littlelemon.navigation.ScreenOnboardingRoute
import com.example.littlelemon.utils.EMAIL
import com.example.littlelemon.utils.FIRST_NAME
import com.example.littlelemon.utils.IS_LOGGED_IN
import com.example.littlelemon.utils.LAST_NAME
import com.example.littlelemon.utils.PreferencesManager

@Composable
fun Profile(navController: NavController) {
    Column {
        Header()
        ReadOnlyForm(navController)
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadOnlyForm(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val sharedPreferences = PreferencesManager(LocalContext.current)
    val loggedIn: MutableState<Boolean> = mutableStateOf(sharedPreferences.getBool(IS_LOGGED_IN, false))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.personal_details),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(vertical = 40.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column() {
                Text(
                    text = "First name",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = sharedPreferences.getData("FIRST_NAME", ""),
                    onValueChange = {  },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp),
                    shape = RoundedCornerShape(9.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }
            Column() {
                Text(
                    text = "Last name",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = sharedPreferences.getData("LAST_NAME", ""),
                    onValueChange = {  },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp),
                    shape = RoundedCornerShape(9.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }
            Column() {
                Text(
                    text = "Email",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = sharedPreferences.getData("EMAIL", ""),
                    onValueChange = {  },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp),
                    shape = RoundedCornerShape(9.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }
        }

        Button(
            onClick = {
                clearForm(sharedPreferences)
                sharedPreferences.saveBool(IS_LOGGED_IN, false)
                loggedIn.value = sharedPreferences.getBool(IS_LOGGED_IN, false)
                navController.navigate(ScreenOnboardingRoute.route)
            },
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.primary_002)
            ),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp)
        )

        {
            // Inner content including an icon and a text label
            Text(
                stringResource(id = R.string.logout),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                color = colorResource(id = R.color.highlight_002),
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

fun clearForm(sharedPreferences: PreferencesManager) {
    sharedPreferences.saveData(FIRST_NAME, "")
    sharedPreferences.saveData(LAST_NAME, "")
    sharedPreferences.saveData(EMAIL, "")
}
