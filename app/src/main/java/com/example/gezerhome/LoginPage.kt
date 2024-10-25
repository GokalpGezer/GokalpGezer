package com.example.gezerhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text as MText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
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
        MText(
            text = "GEZER HOME",
            color = Color.Black,
            style = TextStyle(fontSize = 48.sp),
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .fillMaxWidth(), // Fill the entire available width
            textAlign = TextAlign.Center, // Center the text horizontally
            fontFamily = FontFamily.Serif // Set the font family
        )
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
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 46.dp, y = 125.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 55.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 46.dp, y = 274.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 41.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xff232325))
                .clickable(onClick = onLoginClick) // Invoke onLoginClick when clicked
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 46.dp, y = 372.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 41.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xff232325))
                .clickable(onClick = onRegisterClick) // Invoke onRegisterClick when clicked
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 46.dp, y = 205.dp)
                .requiredWidth(width = 283.dp)
                .requiredHeight(height = 55.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
        )
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "user-solid 1",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 61.dp, y = 143.dp)
                .requiredWidth(width = 17.dp)
                .requiredHeight(height = 19.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.key),
            contentDescription = "key-solid 1",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 59.dp, y = 223.dp)
                .requiredSize(size = 18.dp)
        )
        MText(
            text = "Username",
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 84.dp, y = 139.dp)
        )
        MText(
            text = "Password",
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 84.dp, y = 219.dp)
        )
        MText(
            text = "Login",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 159.dp, y = 279.dp)
                .clickable(onClick = onLoginClick) // Invoke onLoginClick when clicked
        )
        MText(
            text = "Don’t have an account?",
            color = Color.Black,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 91.dp, y = 329.dp)
        )
        MText(
            text = "Register",
            color = Color(0xfffb1010),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 152.dp, y = 380.dp)
                .clickable(onClick = onRegisterClick) // Invoke onRegisterClick when clicked
        )
    }
}
