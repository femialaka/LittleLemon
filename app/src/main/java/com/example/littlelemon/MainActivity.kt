package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.navigation.NavigationComposable
import com.example.littlelemon.screens.Header
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.http.ContentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, MenuDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                saveMenuToDatabase(fetchMenu())
            }
        }

        setContent {
            val databaseMenuItems by database.menuDao().getMenuItems().observeAsState(emptyList())

            //Sort by Title
            NavigationComposable(items = databaseMenuItems.sortedBy { it.title })
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>().menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItems = menuItemsNetwork.map { it.toMenuItem() }
        database.menuDao().insert(menuItems)
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