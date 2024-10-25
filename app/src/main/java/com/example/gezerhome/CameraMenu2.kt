package com.example.gezerhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CameraMenu2(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickPage1: () -> Unit,
    onClickPage2: () -> Unit,
    onClickPage3: () -> Unit
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
                .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "GEZER HOME",
                fontSize = 48.sp,
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Boxes representing images
            repeat(3) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 189.dp)
                        .requiredHeight(height = 159.dp)
                        .background(color = Color(0xffd9d9d9))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.no_signal),
                        contentDescription = "Your content description here",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "arrow-left-solid 3",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 22.dp, y = 91.dp)
                .requiredWidth(width = 60.dp)
                .requiredHeight(height = 54.dp)
                .clickable { onClickBack() }
        )
        // Row for page buttons
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            // Page 1 Button
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { onClickPage1() }
            ) {
                Text(
                    text = "1",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
            // Page 2 Button
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onClickPage2() }
            ) {
                Text(
                    text = "2",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            // Page 3 Button
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onClickPage3() }
            ) {
                Text(
                    text = "3",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    }
}

