package com.example.gezerhome

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun ThermostatMenu(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    // Bluetooth Integration
    val context = LocalContext.current
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    val isBluetoothEnabled = remember { mutableStateOf(bluetoothAdapter?.isEnabled ?: false) }

    val enableBluetoothLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                isBluetoothEnabled.value = true
            } else {
                Toast.makeText(
                    context,
                    "Bluetooth is required for this feature",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    if (!isBluetoothEnabled.value) {
        // Bluetooth is not enabled, ask user to enable it
        val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        enableBluetoothLauncher.launch(enableBluetoothIntent)
    } else {
        // Bluetooth is enabled, perform other Bluetooth operations here
        // For example, device discovery or connecting to a specific device
    }

    // State to hold the temperature value
    val temperatureState = remember { mutableStateOf(21) }

    // UI Elements
    Box(
        modifier = modifier.fillMaxSize().background(Color.White)
    ) {
        // Background Color
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color(0xfff29b17))
        )

        // Text
        Text(
            text = "GEZER HOME",
            color = Color.Black,
            style = TextStyle(fontSize = 48.sp),
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp).fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive, // Set the font family
            fontWeight = FontWeight.Bold
        )

        // Thermostat Image
        Image(
            painter = painterResource(id = R.drawable.thermostat_smart),
            contentDescription = "Thermostat Image",
            modifier = Modifier.align(alignment = Alignment.TopStart)
                .offset(x = 2.dp, y = 127.dp)
                .requiredWidth(width = 555.dp)
                .requiredHeight(height = 381.dp)
        )

        // Plus Button
        Box(
            modifier = Modifier.align(alignment = Alignment.TopStart)
                .offset(x = 79.dp, y = 397.dp)
                .requiredSize(size = 50.dp)
                .clickable { temperatureState.value++ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "plus-solid",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        // Minus Button
        Box(
            modifier = Modifier.align(alignment = Alignment.TopStart)
                .offset(x = 273.dp, y = 397.dp)
                .requiredSize(size = 50.dp)
                .clickable { temperatureState.value-- }
        ) {
            Image(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "minus-solid",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        // Back Arrow Button
        Box(
            modifier = Modifier.align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 91.dp)
                .requiredWidth(width = 60.dp)
                .requiredHeight(height = 54.dp)
                .clickable(onClick = onBackClick)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "back-arrow",
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }

        // Temperature Text
        Text(
            text = temperatureState.value.toString(),
            color = Color.White,
            style = TextStyle(fontSize = 35.sp),
            modifier = Modifier.align(alignment = Alignment.TopStart).offset(x = 179.dp, y = 225.dp)
        )

        // Sun or Snowflake Icon
        val isHot = temperatureState.value >= 21
        val iconResource = if (isHot) R.drawable.sun else R.drawable.snowflake
        val iconColor = if (isHot) Color.Yellow else Color.Cyan
        val iconOffsetX = if (isHot) -50.dp else -50.dp
        Box(
            modifier = Modifier.align(alignment = Alignment.TopEnd)
                .offset(x = iconOffsetX, y = 180.dp)
                .requiredSize(size = 50.dp)
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = if (isHot) "Sun Icon" else "Snowflake Icon",
                colorFilter = ColorFilter.tint(iconColor)
            )
        }

        // Connect to Bluetooth Device Button
        Box(
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .clickable { checkLocationPermission(context) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bluetooth),
                contentDescription = "bluetoothicon"
            )
        }
    }
}

// Function to check location permission and start device discovery
private fun checkLocationPermission(context: Context) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Permission is not granted, request it
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    } else {
        // Permission already granted, start Bluetooth discovery
        startDeviceDiscovery(context)
    }
}

// Function to connect to a Bluetooth device
private fun connectToBluetoothDevice(context: Context, device: BluetoothDevice) {
    // Check if the app has the necessary permissions
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BLUETOOTH
        ) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BLUETOOTH_ADMIN
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // If permissions are not granted, handle accordingly
        // For example, show a message to the user or request permissions
        return
    }

    // Your connection logic goes here
    // For simplicity, we'll just print the device name
    println("Connecting to Bluetooth device: ${device.name}")
}
// Add this constant at the top level of your class
private val LOCATION_PERMISSION_REQUEST_CODE = 100

private fun startDeviceDiscovery(context: Context) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: return

    // Check if the app has the necessary permissions
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BLUETOOTH
        ) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BLUETOOTH_ADMIN
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // If permissions are not granted, handle accordingly
        // For example, show a message to the user or request permissions
        return
    }

    // Check location permission
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Permission is not granted, request it
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
        return
    }

    if (!bluetoothAdapter.isEnabled) {
        // Bluetooth is not enabled
        return
    }

    bluetoothAdapter.startDiscovery()

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Check if the app has the necessary permissions
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.BLUETOOTH
                ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.BLUETOOTH_ADMIN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // If permissions are not granted, handle accordingly
                // For example, show a message to the user or request permissions
                return
            }

            val action = intent?.action
            if (BluetoothDevice.ACTION_FOUND == action) {
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                device?.let {
                    // Connect to the first discovered device
                    connectToBluetoothDevice(context, it)
                    // Cancel discovery after finding the first device
                    bluetoothAdapter.cancelDiscovery()
                }
            }
        }
    }

    val noDeviceFoundReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                // Discovery finished, but no device found
                Toast.makeText(context, "No Bluetooth device found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
    context.registerReceiver(receiver, filter)

    val noDeviceFoundFilter = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
    context.registerReceiver(noDeviceFoundReceiver, noDeviceFoundFilter)
}

