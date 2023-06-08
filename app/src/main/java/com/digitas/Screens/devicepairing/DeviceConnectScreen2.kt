package com.digitas.Screens.devicepairing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.digitas.Screens.BackGroundApp
import com.digitas.iqos.R
import kotlinx.coroutines.delay

@Composable
fun DeviceConnectScreen2(navController: NavHostController) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(0)
        visible = true
    }
    BackGroundApp {
        GifImage(gif = R.raw.lottie2)
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1000, 500))
            ) {
                Text(
                    text = stringResource(R.string.device_enableBluetoothTitle),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.iqosbold)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
                )
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1500, 2000))
            ) {
                Text(
                    text = stringResource(R.string.device_enableBluetoothTxt),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp, start = 24.dp, end = 24.dp)
                )
            }
            Row(modifier = Modifier.padding(bottom = 90.dp)) {

                MyIndicator(
                    indicatorProgress = 1f,
                    navController = navController,
                    launch = false,
                    delay = 0
                )
                Spacer(modifier = Modifier.width(10.dp))
                MyIndicator2(
                    modifier = Modifier.padding(top = 160.dp),
                    indicatorProgress = 0f,
                    navController = navController,
                    true,
                    delay = 0
                )
            }
        }
    }
}

@Composable
@Preview
fun UiCheckConnectionScreen2() {

    DeviceConnectScreen2(navController = NavHostController(LocalContext.current))
}

