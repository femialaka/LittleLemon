package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.screens.AddIntro
import com.example.littlelemon.screens.AddTitle
import com.example.littlelemon.screens.Header


val items = listOf(
    MenuItem(0, "Cheesecake", "delicious", "$14.99", "bruschetta", "desert"),
    MenuItem(1, "Mushroom", "really nice food to eat", "$4.99", "bruschetta", "salad"),
    MenuItem(2, "Chocolate Ice cream", "delicious", "$5.99", "bruschetta", "desert")
)

@Composable
fun HomeDesign(items: List<MenuItem>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Header()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
                .padding(horizontal = 12.dp)
        ) {
            AddTitle()
            AddIntro()
        }
    }
}

@Composable
fun MenuDesignItems(
    items: List<MenuItem>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyColumn {
            itemsIndexed(items) { _, item ->
                MenuDesignItemDetails(item)
            }
        }
    }
}

@Composable
fun MenuDesignItemDetails(menuItem: MenuItem) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = menuItem.title)
    }
}



@Composable
@Preview(showBackground = true, name = "PIXEL_XL", device = Devices.PIXEL_XL)
fun HomeDesignPreview() {
    HomeDesign(items)
}