package com.example.littlelemon.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.littlelemon.MenuItem
import com.example.littlelemon.R
import com.example.littlelemon.navigation.ScreenProfileRoute
import com.example.littlelemon.utils.PreferencesManager

val testItems = listOf(
    MenuItem(0, "Cheesecake", "taste it for yourself and be delicious beyond comprehension the minute I really nice food to eat and if its nice to eat then took a bite it was delicious to taste", "14", "bruschetta", "desert"),
    MenuItem(1, "Mushroom", "really nice food to eat and if its nice to eat then who am I to say no to another portion you just have to go and taste it for yourself and be the judge.", "4", "bruschetta", "salad"),
    MenuItem(2, "Chocolate Ice cream", "I really nice food to eat and if its nice to eat then took a bite it was delicious to taste that is what its all about when it comes to really nice food to eat and if its nice to eat then who am I to say no to another ", "5", "bruschetta", "desert")
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun Home(navController: NavController?, items: List<MenuItem>) {

    val sharedPreferences = PreferencesManager(LocalContext.current)
    val loggedIn: MutableState<Boolean> = mutableStateOf(sharedPreferences.getBool("IS_LOGGED_IN", false))

    var searchPhrase by remember { mutableStateOf("") }
    var currentCategory by remember { mutableStateOf("Starters") }
    val options = listOf("Starters", "Mains", "Desserts", "Drinks")
    var selectedOption by remember { mutableStateOf("") }
    val onSelectionChange = { text: String -> selectedOption = text }

    BackHandler(true) {
        // Disable Back Button
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HomeHeader(navController)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
                .padding(horizontal = 12.dp)
        ) {
            AddTitle()
            AddIntro()

            TextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 8.dp),
                shape = RoundedCornerShape(9.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Add"
                    )
                },
                placeholder = { Text(stringResource(id = R.string.enter_search)) },
                singleLine = true,
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 24.dp, start = 12.dp)) {
            Text(
                text = stringResource(id = R.string.order_for_delivery),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            //Category Buttons
            Row(modifier = Modifier.fillMaxWidth()) {
                options.forEach { text ->
                    Row(modifier = Modifier
                        .padding(end = 6.dp)
                        .weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = text,
                            style = typography.bodyMedium.merge(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = colorResource(id = R.color.highlight_002),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(size = 12.dp))
                                .clickable {
                                    currentCategory = text
                                    onSelectionChange(text)
                                }
                                .background(
                                    if (text == selectedOption) {
                                        colorResource(id = R.color.primary_002)
                                    } else {
                                        colorResource(id = R.color.secondary_002)
                                    }
                                )
                                .padding(9.dp),
                        )
                    }
                }
            }
        }
        Divider(
            color = colorResource(id = R.color.divider),
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        //Check the Search Phrase and Category
        if(searchPhrase.isNotBlank())
            MenuItems(items.filter {
                it.title.contains(searchPhrase, ignoreCase = true) && it.category == currentCategory.lowercase()
            })
        else {
            MenuItems(items.filter {
                it.category == currentCategory.lowercase()
            })
        }
    }
}

@Composable
fun HomeHeader(navController: NavController?) {
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
                .weight(3f)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.logo),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clickable {
                    navController?.navigate(ScreenProfileRoute.route)
                    Log.i("MSG", "Navigate to Profile")
                }
                .height(54.dp)
                .width(54.dp)
                .weight(1f)
        )
    }
}

@Composable
fun AddIntro() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "We are a family owned Mediterranean restaurant, focused on " +
                    "traditional recipes served with a modern twist.",
            //modifier = Modifier.width(180.dp),
            color = colorResource(id = R.color.highlight_001),
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            modifier = Modifier.weight(2f),
            maxLines = 5
        )

        Image(
            painter = painterResource(R.drawable.hero_image),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .weight(1.5f)
                .padding(9.dp)
                .clip(RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun AddTitle() {
    Card {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57)),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                "Little Lemon",
                fontSize = 64.sp,
                fontFamily = FontFamily(Font(R.font.markazitext_variablefont_wght)),
                color = Color.Yellow,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 18.dp)
            )
            Text(
                "Chicago",
                fontSize = 40.sp,
                fontFamily = FontFamily(Font(R.font.markazitext_variablefont_wght)),
                color = Color.White,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun MenuItems(
    items: List<MenuItem>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        LazyColumn {
            itemsIndexed(items) { _, item ->
                MenuItemDetails(item)
            }
        }
    }
}

@Composable
fun MenuItemDetails(menuItem: MenuItem) {
    Column(modifier = Modifier.padding(vertical = 9.dp)) {
        Text(menuItem.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, modifier = Modifier.padding(bottom = 9.dp))
        Row() {
            Column(modifier = Modifier
                .weight(3f)
                .padding(bottom = 9.dp)) {
                Text(menuItem.description, fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,modifier = Modifier.padding(bottom = 9.dp))
                Text("$${menuItem.price.toDouble()}", fontSize = 14.sp, fontWeight = FontWeight.Medium, maxLines = 1)
            }
            Column(modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)) {
                AsyncImage(
                    model = menuItem.image,
                    contentDescription = null,
                )
            }
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .background(colorResource(id = R.color.divider))
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomePreview() {
    Home(null, testItems)
}

