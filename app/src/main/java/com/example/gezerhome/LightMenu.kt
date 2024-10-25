package com.example.gezerhome

import android.Manifest
import android.app.Activity
import android.bluetooth.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val BLUETOOTH_PERMISSION_REQUEST_CODE = 101


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LightMenu(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    val context = LocalContext.current
    var isSwitchOn by remember { mutableStateOf(false) }
    var selectedBulbDrawableId by remember { mutableStateOf(R.drawable.offbulb) }
    var selectedColor by remember { mutableStateOf(Color.White) }
    var bluetoothDeviceFound by remember { mutableStateOf(true) } // Track if Bluetooth device is found

    // Request Bluetooth permissions
    val requestBluetoothPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, initialize Bluetooth
            initializeBluetooth(context)
        } else {
            // Permission denied, handle accordingly
            // For example, show a toast or display an error message
        }
    }

    // Request necessary permissions if not granted
    DisposableEffect(context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestBluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
        }
        onDispose {}
    }

    // Connect to the Bluetooth device
    val bluetoothDevice = remember { BluetoothAdapter.getDefaultAdapter()?.bondedDevices?.find { it.name == "BLEDOM" } }
    if (bluetoothDevice != null) {
        bluetoothDeviceFound = true
        DisposableEffect(Unit) {
            connectToDevice(bluetoothDevice, context)
            onDispose {}
        }
    } else {
        // No Bluetooth device found, set flag to false
        bluetoothDeviceFound = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Light menu UI
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            // Show light menu UI if Bluetooth device is found
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
                    Text(
                        text = "GEZER HOME",
                        color = Color.Black,
                        style = androidx.compose.ui.text.TextStyle(fontSize = 48.sp),
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .fillMaxWidth(), // Fill the entire available width
                        textAlign = TextAlign.Center, // Center the text horizontally
                        fontFamily = FontFamily.Cursive, // Set the font family
                        fontWeight = FontWeight.Bold
                    )

                    // Colored buttons
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = 45.dp, y = 165.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ColoredButton(color = Color(0xfffaff00)) { color, isOn ->
                                val command = getColorCommand(color)
                                sendColorCommand(context, bluetoothGatt, command)
                                selectedBulbDrawableId = when {
                                    isOn -> getBulbDrawableId(color)
                                    else -> R.drawable.offbulb
                                }
                                isSwitchOn = isOn
                                selectedColor = color
                            }
                            Spacer(modifier = Modifier.width(89.dp))
                            ColoredButton(color = Color.White) { color, isOn ->
                                val command = getColorCommand(color)
                                sendColorCommand(context, bluetoothGatt, command)
                                selectedBulbDrawableId = when {
                                    isOn -> getBulbDrawableId(color)
                                    else -> R.drawable.offbulb
                                }
                                isSwitchOn = isOn
                                selectedColor = color
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ColoredButton(color = Color.Cyan) { color, isOn ->
                                val command = getColorCommand(color)
                                sendColorCommand(context, bluetoothGatt, command)
                                selectedBulbDrawableId = when {
                                    isOn -> getBulbDrawableId(color)
                                    else -> R.drawable.offbulb
                                }
                                isSwitchOn = isOn
                                selectedColor = color
                            }
                            Spacer(modifier = Modifier.width(89.dp))
                            ColoredButton(color = Color.Red) { color, isOn ->
                                val command = getColorCommand(color)
                                sendColorCommand(context, bluetoothGatt, command)
                                selectedBulbDrawableId = when {
                                    isOn -> getBulbDrawableId(color)
                                    else -> R.drawable.offbulb
                                }
                                isSwitchOn = isOn
                                selectedColor = color
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ColoredButton(color = Color(0xff0ed145)) { color, isOn ->
                                val command = getColorCommand(color)
                                sendColorCommand(context, bluetoothGatt, command)
                                selectedBulbDrawableId = when {
                                    isOn -> getBulbDrawableId(color)
                                    else -> R.drawable.offbulb
                                }
                                isSwitchOn = isOn
                                selectedColor = color
                            }
                            Spacer(modifier = Modifier.width(89.dp))
                            ColoredButton(color = Color(0xff001aff)) { color, isOn ->
                                val command = getColorCommand(color)
                                sendColorCommand(context, bluetoothGatt, command)
                                selectedBulbDrawableId = when {
                                    isOn -> getBulbDrawableId(color)
                                    else -> R.drawable.offbulb
                                }
                                isSwitchOn = isOn
                                selectedColor = color
                            }
                        }
                    }

                    OnOffSwitch(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 265.dp, y = 100.dp),
                        isOn = isSwitchOn
                    ) { isOn ->
                        isSwitchOn = isOn
                        selectedBulbDrawableId = if (isSwitchOn) getBulbDrawableId(selectedColor) else R.drawable.offbulb
                    }

                    LightBulbIcon(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = -5.dp, y = 490.dp),
                        bulbDrawableId = selectedBulbDrawableId
                    )

                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "arrow-left-solid 1",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 21.dp, y = 91.dp)
                            .requiredWidth(width = 60.dp)
                            .requiredHeight(height = 54.dp)
                            .clickable { onClickBack() }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.bluetooth),
                        contentDescription = "bluetoothicon",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 200.dp, y = 510.dp)
                            .clickable { checkLocationPermission(context) }
                    )
                }
            }
        }
    }
}

@Composable
fun ColoredButton(
    color: Color,
    onClick: (Color, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .size(105.dp, 94.dp)
            .background(color = color)
            .clickable {
                // Update selected color and isOn when clicked
                onClick(color, true)
            }
    )
}

@Composable
fun OnOffSwitch(
    modifier: Modifier = Modifier,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val switchColor = if (isOn) Color.Green else Color.Red
    Box(
        modifier = modifier
            .size(width = 60.dp, height = 30.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.Gray)
            .clickable { onToggle(!isOn) }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isOn) {
                Text(
                    text = "On",
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .offset(x = if (isOn) 0.dp else 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(color = switchColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Customize the appearance of the circle here
                }
            }
            if (!isOn) {
                Text(
                    text = "Off",
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Composable
fun LightBulbIcon(
    modifier: Modifier = Modifier,
    bulbDrawableId: Int // Drawable resource ID for the bulb image
) {
    Image(
        painter = painterResource(id = bulbDrawableId),
        contentDescription = "Light bulb",
        modifier = modifier
            .requiredWidth(200.dp)
            .requiredHeight(200.dp)
    )
}

fun getBulbDrawableId(color: Color): Int {
    return when (color) {
        Color.White -> R.drawable.whitebulb
        Color(0xfffaff00) -> R.drawable.yellowbulb
        Color.Cyan -> R.drawable.cyanbulb
        Color.Red -> R.drawable.redbulb
        Color(0xff0ed145) -> R.drawable.greenbulb
        Color(0xff001aff) -> R.drawable.bluebulb
        else -> R.drawable.offbulb
    }
}

fun initializeBluetooth(context: Context) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    if (bluetoothAdapter == null) {
        // Device doesn't support Bluetooth
        Toast.makeText(context, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show()
        return
    }

    // Check if Bluetooth permissions are granted
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BLUETOOTH
        ) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BLUETOOTH_ADMIN
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // If permissions are not granted, request them
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
            ),
            BLUETOOTH_PERMISSION_REQUEST_CODE
        )
        return
    }

    // Check if Bluetooth is enabled
    if (!bluetoothAdapter.isEnabled) {
        // Bluetooth is not enabled, prompt user to enable it
        val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        context.startActivity(enableBluetoothIntent)
    } else {
        // Bluetooth is enabled, start device discovery
        startDeviceDiscovery(context)
    }
}


var bluetoothGatt: BluetoothGatt? = null // Define the BluetoothGatt object

fun connectToDevice(device: BluetoothDevice, context: Context) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
        return
    }
    try {
        bluetoothGatt = device.connectGatt(context, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
                super.onConnectionStateChange(gatt, status, newState)
                when (newState) {
                    BluetoothProfile.STATE_CONNECTED -> {
                        Log.d("Bluetooth", "Connected to ${device.name}")
                        gatt?.discoverServices()
                        // Show toast indicating successful connection
                        showToast(context, "Connected to ${device.name}")
                    }
                    BluetoothProfile.STATE_DISCONNECTED -> {
                        Log.d("Bluetooth", "Disconnected from ${device.name}")
                        Log.e("Bluetooth", "Bluetooth connection failed")
                        // Show toast indicating connection failure
                        showToast(context, "Failed to connect to ${device.name}")
                    }
                }
            }
            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                super.onServicesDiscovered(gatt, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    Log.d("Bluetooth", "Services discovered")
                } else {
                    Log.e("Bluetooth", "Service discovery failed")
                }
            }
        })
    } catch (e: SecurityException) {
        Log.e("Bluetooth", "SecurityException: ${e.message}")
    }
}

// Function to show a toast message
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun sendColorCommand(context: Context, gatt: BluetoothGatt?, command: ByteArray) {
    // Check if BluetoothGatt object and command are not null
    if (gatt == null || command.isEmpty()) {
        Log.e("Bluetooth", "BluetoothGatt object or command is null")
        return
    }

    // Get the list of services from the connected device
    val services = gatt.services

    // Iterate through each service
    for (service in services) {
        // Get the list of characteristics for each service
        val characteristics = service.characteristics

        // Iterate through each characteristic
        for (characteristic in characteristics) {
            // Check if the characteristic has write property
            if (characteristic.properties and BluetoothGattCharacteristic.PROPERTY_WRITE > 0) {
                // Write the command to the characteristic
                characteristic.value = command
                try {
                    // Write the characteristic
                    gatt.writeCharacteristic(characteristic)
                    return  // Exit the function after writing to the characteristic
                } catch (e: SecurityException) {
                    Log.e("Bluetooth", "SecurityException while writing characteristic: ${e.message}")
                }
            }
        }
    }

    // If no writable characteristic is found, log an error
    Log.e("Bluetooth", "No writable characteristic found")
}

fun getColorCommand(color: Color): ByteArray {
    return when {
        color == Color.White -> byteArrayOf(0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte())
        color == Color(0xfffaff00) -> byteArrayOf(0xFF.toByte(), 0xFF.toByte(), 0x00.toByte())
        color == Color.Cyan -> byteArrayOf(0x00.toByte(), 0xFF.toByte(), 0xFF.toByte())
        color == Color.Red -> byteArrayOf(0xFF.toByte(), 0x00.toByte(), 0x00.toByte())
        color == Color(0xff0ed145) -> byteArrayOf(0x00.toByte(), 0xFF.toByte(), 0x00.toByte())
        color == Color(0xff001aff) -> byteArrayOf(0x00.toByte(), 0x00.toByte(), 0xFF.toByte())
        else -> byteArrayOf(0x00.toByte(), 0x00.toByte(), 0x00.toByte())
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

