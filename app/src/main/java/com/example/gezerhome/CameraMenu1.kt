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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun CameraMenu1(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    val context = LocalContext.current
    var numberOfCameras by remember { mutableStateOf(0) } // State to hold the number of cameras

    // Fetch the number of cameras when the composable is initially composed
    LaunchedEffect(key1 = true) {
        fetchNumberOfCameras(
            onSuccess = { count ->
                numberOfCameras = count
            },
            onError = { error ->
                // Handle error if any
                println("Error fetching number of cameras: $error")
            }
        )
    }

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
                modifier = Modifier.padding(bottom = 32.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold

                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            // Display the number of cameras
            Box(
                modifier = Modifier
                    .requiredWidth(width = 306.dp)
                    .requiredHeight(height = 85.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff232325))
            ) {
                Text(
                    text = "You have $numberOfCameras cameras.",
                    style = TextStyle(fontSize = 30.sp,
                        fontFamily = FontFamily.Cursive),
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
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
        // House Image at the bottom
        Image(
            painter = painterResource(id = R.drawable.camera), // Assuming "house.png" is in the drawable resources
            contentDescription = "Camera Image",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .size(width = 268.dp, height = 268.dp),
            contentScale = ContentScale.FillBounds
        )

        // Row for page buttons
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 306.dp)
                    .requiredHeight(height = 85.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff232325))
                    .clickable {
                        val videoUrl = "http://172.20.10.4:5000/camera/1"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                        context.startActivity(intent)
                    }
            ) {
                androidx.compose.material3.Text(
                    text = "See Camera Footages",
                    color = Color.Red,
                    style = TextStyle(fontSize = 30.sp,
                        fontFamily = FontFamily.Cursive),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

// Function to fetch the number of cameras
fun fetchNumberOfCameras(onSuccess: (Int) -> Unit, onError: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val url = URL("http://172.20.10.4:5000/footages")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(response)
                onSuccess(jsonArray.length()) // Pass the length of the JSONArray to the success callback
            } else {
                onError("Failed to fetch data. Response code: $responseCode")
            }
        } catch (e: Exception) {
            onError(e.message ?: "An error occurred while fetching data")
        }
    }
}