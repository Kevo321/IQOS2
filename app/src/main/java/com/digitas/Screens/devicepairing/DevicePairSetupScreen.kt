package com.digitas.Screens.devicepairing

import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import coil.size.Scale
import com.airbnb.lottie.compose.*
import com.digitas.Screens.BackGroundApp
import com.digitas.Screens.StandardButton
import com.digitas.iqos.R
import com.digitas.navigation.Screen
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DevicePairSetupScreen(navController: NavHostController) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(0)
        visible = true
    }
    var screenVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(100)
        screenVisible = true
    }
    BackGroundApp {
        AnimatedVisibility(
            visible = screenVisible,
            enter = fadeIn(animationSpec = tween(1000, 500))
        ) {
            GifImage()
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, end = 24.dp)
                ) {
                    SkipButton()
                }
                Spacer(modifier = Modifier.weight(.1f))
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(1000, 500))
                ) {
                    Text(
                        text = stringResource(R.string.device_pairingTitle),
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
                        text = stringResource(R.string.device_pairingTitle_info),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.iqos_regular)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp, start = 24.dp, end = 24.dp)
                    )
                }
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(1500, 2000)),
                    modifier = Modifier.padding(bottom = 62.dp)
                ) {
                    StandardButton(
                        onClick = { navController.navigate(Screen.DeviceConnectScreen1.route) },
                        text = stringResource(id = R.string.connect_ilumia),
                        withArrow = true
                    )
                }
            }
        }
    }
}

@Composable
fun SkipButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    TextButton(onClick = { Toast.makeText(context, "Skip button", Toast.LENGTH_SHORT).show() }) {
        Text(
            text = stringResource(id = R.string.skip_to_app), color = Color.White,
            textDecoration = TextDecoration.Underline,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.iqos_regular))
        )
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier, gif: Any = R.raw.lottie1
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()
    Image(
        painter = rememberImagePainter(
            ImageRequest.Builder(context).data(gif).apply(block = {
                scale(Scale.FIT)
                repeatCount(0)
            }).build(), imageLoader = imageLoader
        ),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
    )
}


@Composable
@Preview
fun UiCheck() {
    DevicePairSetupScreen(navController = NavHostController(LocalContext.current))
}