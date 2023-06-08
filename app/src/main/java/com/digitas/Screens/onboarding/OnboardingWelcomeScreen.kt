package com.digitas.Screens


import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.digitas.iqos.R
import com.digitas.navigation.Screen
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingWelcomeScreen(navController: NavHostController) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(0)
        visible = true
    }
    BackGroundApp {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1.0f))
            AnimatedVisibility(
                visible = visible,
                enter = scaleIn(animationSpec = tween(1400, easing = EaseInBounce))
            ) {
                LoadIcon(100.dp, resource = R.drawable.avatar)
            }
            Spacer(modifier = Modifier.weight(1.0f))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(2000, 1000))
            ) {
                Text(
                    text = stringResource(id = R.string.welcomeToTheCommunity),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(312.dp),
                    lineHeight = 40.sp, fontFamily = FontFamily(Font(R.font.iqosbold))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(3000, 1900))
            ) {
                Text(
                    text = stringResource(id = R.string.welcomeInfoTxt1),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(3000, 1900))
            ) {
                Text(
                    text = stringResource(id = R.string.welcomeInfoTxt2),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(2000, 2900))
            ) {
                GetStartedButton(navController)
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(2000, 2900))
            ) {
                SkipButton()
            }
        }
    }
}

@Composable
fun LoadIcon(
    size: Dp,
    modifier: Modifier = Modifier,
    @DrawableRes resource: Int? = R.drawable.icon
) {
    val painter = rememberImagePainter(resource)
    Image(
        painter = painter, contentDescription = "Image",
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun GetStartedButton(navController: NavHostController) {
    StandardButton(
        onClick = { navController.navigate(Screen.OnboardingJourneyScreen.route) },
        text = stringResource(id = R.string.buttonGetStarted),
        withArrow = true
    )
}

@Composable
fun SkipButton() {
    val context = LocalContext.current
    TextButton(onClick = { Toast.makeText(context, "Skip button", Toast.LENGTH_SHORT).show() }) {
        Text(
            text = "Skip", color = Color.White,
            textDecoration = TextDecoration.Underline,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.iqos_regular)),
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
    }
}

@Composable
@Preview
fun UiCheckOnboardingWelcomeScreen() {

    OnBoardingWelcomeScreen(navController = NavHostController(LocalContext.current))
}