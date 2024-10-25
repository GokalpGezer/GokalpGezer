package com.example.gezerhome

// MainActivity.kt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gezerhome.ui.theme.GezerHomeTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GezerHomeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Show login page initially
                    FirstPageWrapper()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun FirstPageWrapper() {
    var isLoginPageVisible by remember { mutableStateOf(false) }
    var isRegisterPageVisible by remember { mutableStateOf(false) }
    var isFunctionselmenuVisible by remember { mutableStateOf(true)}
    var isThermostatVisible by remember { mutableStateOf(false)}
    var isLightmenuVisible by remember { mutableStateOf(false)}
    var isCameramenu1Visible by remember { mutableStateOf(false)}
    var isCameramenu2Visible by remember { mutableStateOf(false)}
    var isCameramenu3Visible by remember { mutableStateOf(false)}
    var isCamMonitorMenuVisible by remember { mutableStateOf(false)}
    var isPCMonitorMenuVisible by remember { mutableStateOf(false)}



   /* if (isLoginPageVisible) {
        LoginPage(
            modifier = Modifier,
            onRegisterClick = {
            isRegisterPageVisible = true
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }, // Pass the lambda function to handle register click
            onLoginClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
        )
    } else if (isRegisterPageVisible) {
        RegisterPage(
            modifier = Modifier,
            onConfirmClick = {
            // Handle confirmation and navigate back to the login page
            isLoginPageVisible = true
            isRegisterPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
        )
    }*/
    if (isFunctionselmenuVisible) {
        FunctionSelectMenu(
            modifier = Modifier,
        onThermostatClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = true
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
        },
        onLightClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = true
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
        },
            onCameraClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = true
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
            )
    }
    else if (isThermostatVisible){
        ThermostatMenu(
            modifier = Modifier,
            onBackClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
            )
    }
    else if (isLightmenuVisible) {
        LightMenu(
            modifier = Modifier,
            onClickBack = {
                // Handle back button click
                isRegisterPageVisible = false
                isLoginPageVisible = false
                isFunctionselmenuVisible = true
                isThermostatVisible = false
                isLightmenuVisible = false
                isCameramenu1Visible = false
                isCameramenu2Visible = false
                isCameramenu3Visible = false
                isCamMonitorMenuVisible = false
                isPCMonitorMenuVisible = false
            }
        )
    }

    else if (isCameramenu1Visible){
        CameraMenu1(
            modifier = Modifier,
            onClickBack = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible  = false
            isPCMonitorMenuVisible = false
            }
            )
    }
    else if(isCameramenu2Visible){
        CameraMenu2(
            modifier = Modifier,
            onClickBack = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onClickPage1 = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = true
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onClickPage2 = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = true
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onClickPage3 = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = true
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
        )
    }
    else if (isCameramenu3Visible){
        CameraMenu3(
            modifier = Modifier,
            onClickBack = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onClickPage1 = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = true
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onClickPage2 = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = true
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onClickPage3 = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = true
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
        )
    }
   /* else if (isCamMonitorMenuVisible){
        CameraMonitoringSelect(
            modifier = Modifier,
            onMobileSelect = {
            isLoginPageVisible = false
            isRegisterPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = true
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onBackClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onComputerSelect = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = true
            }
        )
    }*/
    else if (isPCMonitorMenuVisible){
        PCMonitoring(
            modifier = Modifier,
            onBackClick = {
            isRegisterPageVisible = false
            isLoginPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
        )
    }
    else {
        LoginRegisterSelectMenu(
            modifier = Modifier,
            onLoginClick = {
            // Handle login button click here
            // For now, just set isLoginPageVisible to true
            isLoginPageVisible = true
            isRegisterPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onRegisterClick = {
            // Handle register button click here
            // For now, just set isRegisterPageVisible to true
            isRegisterPageVisible = true
            isLoginPageVisible = false
            isFunctionselmenuVisible = false
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            },
            onGuestClick = {
            isLoginPageVisible = false
            isRegisterPageVisible = false
            isFunctionselmenuVisible = true
            isThermostatVisible = false
            isLightmenuVisible = false
            isCameramenu1Visible = false
            isCameramenu2Visible = false
            isCameramenu3Visible = false
            isCamMonitorMenuVisible = false
            isPCMonitorMenuVisible = false
            }
        )
    }
}