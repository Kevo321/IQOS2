package com.digitas.Screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.digitas.Screens.BackGroundApp
import com.digitas.Screens.LoadIcon
import com.digitas.iqos.R
import com.digitas.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun OnboardingScreen_3(navController: NavHostController) {
    var onClickPressed by remember { mutableStateOf(false) }
    BackGroundApp {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            EmbeddedBubblePicker(R.array.notifications, R.array.radiouses3)
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = stringResource(id = R.string.onboardingScreenThreeTitle),
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
                text = stringResource(id = R.string.onboardingScreenThreeTxt),
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
                val (iProfile, btnSkip) = createRefs()
                Box(modifier = Modifier
                    .padding(top = 30.dp)
                    .constrainAs(iProfile) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    if (!onClickPressed) {
                        CircularProgressBar(
                            percentage = .75f,
                            maxProgress = 100,
                            progressValue = .5f
                        )
                    }
                    LoadIcon(77.dp, Modifier.padding(start = 6.dp, top = 5.dp))
                    if (onClickPressed) {
                        CircularProgressBar(
                            percentage = .99f,
                            maxProgress = 100,
                            progressValue = .75f
                        )
                        LaunchedEffect(Unit) {
                            delay(2000)
                            navController.navigate(Screen.DevicePairingSetupScreen.route)
                        }
                    }
                }
                TextButton(
                    modifier = Modifier
                        .padding(top = 30.dp, start = 20.dp)
                        .constrainAs(btnSkip) {
                            start.linkTo(iProfile.end)
                            top.linkTo(iProfile.top)
                            bottom.linkTo(iProfile.bottom)
                        },
                    onClick = {
                        onClickPressed = true
                    }) {
                    Text(
                        text = "Next",
                        color = Color.White,
                        fontSize = 16.sp,
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
@Preview
fun UiCheckOnboardingScreen3() {
    OnboardingScreen_3(navController = NavHostController(LocalContext.current))
}