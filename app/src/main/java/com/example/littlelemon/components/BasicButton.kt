package com.example.littlelemon.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonName: String
) {
    Button(
        onClick = onClick,
        //shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFEDEFEE)),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 1.dp)

    ) {
        Text(
            text = buttonName,
            color = Color(0xFF495E57),
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            modifier = Modifier.padding(horizontal = 0.dp)

        )
    }
}