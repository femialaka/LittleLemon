package com.example.littlelemon.screens

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.navigation.ScreenHomeRoute
import com.example.littlelemon.utils.EMAIL
import com.example.littlelemon.utils.FIRST_NAME
import com.example.littlelemon.utils.IS_LOGGED_IN
import com.example.littlelemon.utils.LAST_NAME
import com.example.littlelemon.utils.PreferencesManager

@Composable
fun Onboarding(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header()
        Banner()
        AddForm(navController = navController)
    }
}

@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(36.dp)
                .width(150.dp)
        )
    }
}

@Composable
fun Banner() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(colorResource(id = R.color.primary_001))
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(100.dp)
    ) {
        Text(
            text = "Let's get to know you",
            color = colorResource(id = R.color.highlight_001),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            fontSize = 22.sp
        )
    }
}


fun validate(email: String): Boolean { return email.isNotEmpty()}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddForm(navController: NavController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var isFirstNameError by remember { mutableStateOf(false) }
    var firstNameErrorMessage by remember { mutableStateOf("") }
    var isLastNameError by remember { mutableStateOf(false) }
    var lastNameErrorMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val sharedPreferences = PreferencesManager(LocalContext.current)
    val loggedIn: MutableState<Boolean> = mutableStateOf(sharedPreferences.getBool(IS_LOGGED_IN, false))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "Personal Information ${loggedIn.value}",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
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
                    value = firstName,
                    onValueChange = { firstName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp),
                    shape = RoundedCornerShape(9.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
                )
                if (isFirstNameError) {
                    firstNameErrorMessage = stringResource(id = R.string.first_name_error)
                    Text(
                        text = firstNameErrorMessage,
                        color = MaterialTheme.colorScheme.error,
                        //style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
            Column() {
                Text(
                    text = "Last name",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp),
                    shape = RoundedCornerShape(9.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
                )
                if (isLastNameError) {
                    lastNameErrorMessage = stringResource(id = R.string.last_name_error)
                    Text(
                        text = lastNameErrorMessage,
                        color = MaterialTheme.colorScheme.error,
                        //style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
            Column() {
                Text(
                    text = "Email",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp),
                    shape = RoundedCornerShape(9.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
                    singleLine = true,
                    isError = isEmailError,
                )
                if (isEmailError) {
                    emailErrorMessage = if(email.isEmpty()) {
                        stringResource(id = R.string.email_empty_error)
                    } else{
                        stringResource(id = R.string.email_error_message)
                    }
                    Text(
                        text = emailErrorMessage,
                        color = MaterialTheme.colorScheme.error,
                        //style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                isEmailError = checkEmail(email)

                isFirstNameError = checkName(firstName)

                isLastNameError = checkName(lastName)

               if(!isEmailError && !isFirstNameError &&  !isLastNameError ) {
                   sharedPreferences.saveData(FIRST_NAME, firstName)
                   sharedPreferences.saveData(LAST_NAME, lastName)
                   sharedPreferences.saveData(EMAIL, email)

                   sharedPreferences.saveBool(IS_LOGGED_IN, true)

                   navController.navigate(ScreenHomeRoute.route)
               }
            },
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.primary_002)
            ),
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier.fillMaxWidth()
        )

        {
            // Inner content including an icon and a text label
            Text(
                "Register",
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                color = colorResource(id = R.color.highlight_002),
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

fun checkEmail(email: String): Boolean {
    return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun checkName(name: String): Boolean { return name.isEmpty() }

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    val navController = rememberNavController()
    Onboarding(navController = navController)
}
