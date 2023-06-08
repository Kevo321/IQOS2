package com.digitas.Screens.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.digitas.Screens.BackGroundApp
import com.digitas.Screens.LoadIcon
import com.digitas.iqos.R
import com.digitas.navigation.Screen

@Composable
fun OnboardingScreen_1(navController: NavHostController) {
    BackGroundApp {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            EmbeddedBubblePicker(R.array.flavours, R.array.radiouses1)
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = stringResource(id = R.string.onboardingScreenOneTitle),
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.iqosbold)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp, top = 40.dp)
            )
            Text(
                text = stringResource(id = R.string.onboardingScreenOneTxt),
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.iqos_regular)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            )
            Spacer(modifier = Modifier.weight(1.0f))
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            ) {
                val (iProfile, btnSkip, background) = createRefs()
                Box(modifier = Modifier
                    .padding(top = 30.dp, bottom = 0.dp)
                    .constrainAs(iProfile) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    CircularProgressBar(percentage = .25f, maxProgress = 100)
                    LoadIcon(77.dp, Modifier.padding(start = 6.dp, top = 5.dp))
                }
                TextButton(modifier = Modifier
                    .padding(top = 30.dp, start = 20.dp)
                    .constrainAs(btnSkip) {
                        start.linkTo(iProfile.end)
                        top.linkTo(iProfile.top)
                        bottom.linkTo(iProfile.bottom)
                    }, onClick = { navController.navigate(Screen.OnboardingScreen_2.route) }) {
                    Text(
                        text = "Next",
                        color = Color.White,
                        fontSize = 16.sp,
                        letterSpacing = 1.5.sp,
                        fontFamily = FontFamily(Font(R.font.iqos_regular)),
                        modifier = Modifier.padding(start = 13.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.arrowright),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 10.dp, top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    maxProgress: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 60.dp,
    color: Color = com.digitas.iqos.ui.theme.turquoise,
    strokeWidth: Dp = 10.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    modifier: Modifier = Modifier,
    progressValue: Float = 0f
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else progressValue,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = animationDuration)
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.wrapContentWidth()) {
        Canvas(modifier = Modifier.size(88.dp)) {
            drawCircle(Color.LightGray, style = stroke, alpha = 0.1f)
            drawArc(
                color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Square)
            )
        }
    }
}

@Composable
@Preview
fun UiCheckOnboardingScreen1() {
    OnboardingScreen_1(navController = NavHostController(LocalContext.current))
}



