package com.example.gezerhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CameraMonitoringSelect(
    modifier: Modifier = Modifier,
    onMobileSelect: () -> Unit,
    onComputerSelect: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xfff29b17))
        )
        Text(
            text = "GEZER HOME",
            color = Color.Black,
            style = TextStyle(fontSize = 48.sp),
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(), // Fill the entire available width
            textAlign = TextAlign.Center, // Center the text horizontally
            fontFamily = FontFamily.Serif // Set the font family
        )
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "arrow-left-solid 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 22.dp, y = 91.dp)
                .requiredWidth(width = 60.dp)
                .requiredHeight(height = 54.dp)
                .clickable { onBackClick() }
        )
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x=40.dp ,y = 164.dp)
                .requiredWidth(width = 306.dp)
                .requiredHeight(height = 170.dp), // Adjusted height here
            verticalArrangement = Arrangement.Top, // Changed from verticalArrangementColumn to Column
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .requiredHeight(height = 85.dp)
                    .requiredWidth(width = 306.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xFF232325))
                    .clickable(onClick = onMobileSelect)
            ) {
                Text(
                    text = "Mobile",
                    color = Color.White,
                    style = TextStyle(fontSize = 35.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(136.dp))
            Box(
                modifier = Modifier
                    .requiredHeight(height = 85.dp)
                    .requiredWidth(width = 306.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xFF232325))
                    .clickable(onClick = onComputerSelect)
            ) {
                Text(
                    text = "Computer",
                    color = Color.White,
                    style = TextStyle(fontSize = 35.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        // House Image at the bottom
        Image(
            painter = painterResource(id = R.drawable.house), // Assuming "house.png" is in the drawable resources
            contentDescription = "House Image",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .size(width = 268.dp, height = 268.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

