package com.example.gezerhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginRegisterSelectMenu(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit, // Lambda for handling login button click
    onRegisterClick: () -> Unit, // Lambda for handling register button click
    onGuestClick: () -> Unit // Lambda for handling guest button click
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
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
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                // Gezer Home Text
                Text(
                    text = "GEZER HOME",
                    color = Color.Black,
                    style = TextStyle(fontSize = 48.sp),
                    modifier = Modifier
                        .padding(top = 32.dp,bottom = 16.dp)
                        .fillMaxWidth(), // Fill the entire available width
                    textAlign = TextAlign.Center, // Center the text horizontally
                    fontFamily = FontFamily.Serif // Set the font family
                )
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Login Button
                    val circleShape = CircleShape
                    Button(
                        onClick = onLoginClick, // Invoke onLoginClick when the button is clicked
                        modifier = Modifier
                            .padding(top = 1.dp)
                            .size(width = 306.dp, height = 85.dp)
                            .clip(circleShape) // Use CircleShape here
                            .background(color = Color(0xFF232325)),
                        content = {
                            Text(
                                text = "Login",
                                fontSize = 35.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color.White
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Register Button
                    Button(
                        onClick = onRegisterClick, // Invoke onRegisterClick when the button is clicked
                        modifier = Modifier
                            .size(width = 306.dp, height = 85.dp)
                            .clip(CircleShape) // Use CircleShape here
                            .background(color = Color.Transparent),
                        content = {
                            Text(
                                text = "Register",
                                fontSize = 35.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color.White
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = onGuestClick, // Invoke onLoginClick when the button is clicked
                        modifier = Modifier
                            .size(width = 306.dp, height = 85.dp)
                            .clip(CircleShape) // Use CircleShape here
                            .background(color = Color(0xFF232325)),
                        content = {
                            Text(
                                text = "Continue As Guest",
                                fontSize = 29.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color.Red
                            )
                        }
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
}
