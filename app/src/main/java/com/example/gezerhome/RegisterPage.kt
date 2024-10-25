package com.example.gezerhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterPage(modifier: Modifier = Modifier, onConfirmClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xfff29b17))
    ) {
        Text(
            text = "GEZER HOME",
            color = Color.Black,
            style = TextStyle(fontSize = 48.sp),
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .fillMaxWidth(), // Fill the entire available width
            textAlign = TextAlign.Center, // Center the text horizontally
            fontFamily = FontFamily.Serif // Set the font family
        )
        // Username input
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp, y = 253.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 55.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
        )
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "user-solid 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 53.dp, y = 271.dp)
                .requiredWidth(width = 17.dp)
                .requiredHeight(height = 19.dp)
        )
        Text(
            text = "Username",
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 76.dp, y = 267.dp)
        )
        // Password input
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp, y = 322.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 55.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
        )
        Image(
            painter = painterResource(id = R.drawable.key),
            contentDescription = "key-solid 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 51.dp, y = 340.dp)
                .requiredSize(size = 18.dp)
        )
        Text(
            text = "Password",
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 76.dp, y = 336.dp)
        )
        // Surname input
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp, y = 184.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 55.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
        )
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "user-solid 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 53.dp, y = 202.dp)
                .requiredWidth(width = 17.dp)
                .requiredHeight(height = 19.dp)
        )
        Text(
            text = "Surname",
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 76.dp, y = 198.dp)
        )
        // Name input
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp, y = 115.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 55.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
        )
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "user-solid 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 53.dp, y = 133.dp)
                .requiredWidth(width = 17.dp)
                .requiredHeight(height = 19.dp)
        )
        Text(
            text = "Name",
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 76.dp, y = 129.dp)
        )
        // Confirm button
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp, y = 391.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 41.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xff232325))
                .clickable {
                    // Call onConfirmClick callback when the confirm button is clicked
                    onConfirmClick()}
        ) {
            Text(
                text = "Confirm",
                color = Color.White,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        // Already have an account text
        Text(
            text = "Already have an account?",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 75.dp, y = 446.dp)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp, y = 480.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 41.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xff232325))
                .clickable {
                    // Call onConfirmClick callback when the confirm button is clicked
                    onConfirmClick()}
        ) {
            // Log in text
            Text(
                text = "Log in",
                color = Color.Red,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}