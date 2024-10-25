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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun FunctionSelectMenu(
    modifier: Modifier = Modifier,
    onThermostatClick: () -> Unit,
    onLightClick: () -> Unit,
    onCameraClick: () -> Unit
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            // Text "GEZER HOME"
            Text(
                text = "GEZER HOME",
                color = Color.Black,
                style = TextStyle(fontSize = 48.sp),
                modifier = Modifier
                    .padding(top = 32.dp,bottom = 16.dp)
                    .fillMaxWidth(), // Fill the entire available width
                textAlign = TextAlign.Center, // Center the text horizontally
                fontFamily = FontFamily.Cursive, // Set the font family
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(16.dp))
            // Lights button
            Box(
                modifier = Modifier
                    .requiredWidth(width = 306.dp)
                    .requiredHeight(height = 85.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff232325))
                    .clickable { onLightClick() }
            ) {
                Text(
                    text = "Lights",
                    color = Color.White,
                    style = TextStyle(fontSize = 45.sp,
                        fontFamily = FontFamily.Cursive),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Thermostat button
            Box(
                modifier = Modifier
                    .requiredWidth(width = 306.dp)
                    .requiredHeight(height = 85.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff232325))
                    .clickable { onThermostatClick() }
            ) {
                Text(
                    text = "Thermostat",
                    color = Color.White,
                    style = TextStyle(fontSize = 45.sp,
                        fontFamily = FontFamily.Cursive),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cameras button
            Box(
                modifier = Modifier
                    .requiredWidth(width = 306.dp)
                    .requiredHeight(height = 85.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff232325))
                    .clickable { onCameraClick() }
            ) {
                Text(
                    text = "Cameras",
                    color = Color.White,
                    style = TextStyle(fontSize = 45.sp,
                        fontFamily = FontFamily.Cursive),
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



