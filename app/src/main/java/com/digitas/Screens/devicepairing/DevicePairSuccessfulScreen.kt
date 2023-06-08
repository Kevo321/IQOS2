package com.digitas.Screens.devicepairing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.digitas.Screens.BackGroundApp
import com.digitas.Screens.StandardButton
import com.digitas.iqos.R
import com.digitas.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun DevicePairSuccessfulScreen(navController: NavHostController) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(0)
        visible = true
    }
    var tickVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        tickVisible = true
    }
    BackGroundApp {
        ConstraintLayout() {
            val (gif, tick) = createRefs()
            GifImage(gif = R.raw.lottie3, modifier = Modifier
                .constrainAs(gif) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .fillMaxHeight())
            if (tickVisible) {
                loaderAnimation(modifier = Modifier.padding(bottom = 120.dp).constrainAs(tick) {
                    start.linkTo(gif.start)
                    end.linkTo(gif.end)
                    top.linkTo(gif.top)
                    bottom.linkTo(gif.bottom, margin = 150.dp)
                })
            }

        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1600, 200))
            ) {
                Text(
                    text = stringResource(R.string.device_successfullyPairdTitle),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.iqosbold)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 68.dp)
                )
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1800, 1200)),
                modifier = Modifier.padding(bottom = 62.dp)
            ) {
                StandardButton(
                    onClick = { navController.navigate(Screen.SelectionScreen.route) },
                    text = stringResource(id = R.string.buttonGetStarted),
                    withArrow = true
                )
            }
        }
    }
}

@Composable
fun loaderAnimation(modifier: Modifier = Modifier) {
    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.circletick)
    )
    val progressAnimation by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = 1,
        speed = 1.0f,

        )
    LottieAnimation(
        composition = compositionResult.value, progress = progressAnimation,
        modifier = modifier
            .alpha(0.8f)
            .width(120.dp)
            .height(120.dp)
    )
}

@Composable
@Preview
fun DevicePairSuccessfulScreenUiCheck() {
    DevicePairSuccessfulScreen(navController = NavHostController(LocalContext.current))
}