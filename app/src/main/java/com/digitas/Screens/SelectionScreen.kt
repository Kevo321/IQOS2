package com.digitas.Screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.digitas.iqos.R
import com.digitas.iqos.ui.theme.SoftWhite
import com.digitas.iqos.ui.theme.Slate
import com.digitas.iqos.ui.theme.bg_end_color
import com.digitas.iqos.ui.theme.bg_start_color
import com.digitas.navigation.Screen

@Composable
fun BackGroundApp(contents: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        bg_start_color,
                        bg_end_color
                    )
                )
            ),
        content = {
            Image(
                painter = painterResource(id = R.drawable.background_vector),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            contents.invoke(this)
        },
    )
}

@Composable
fun JourneySelectorScreen(navController: NavHostController) {
    BackGroundApp {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            OnBoardingButton(navController)
            Spacer(modifier = Modifier.height(20.dp))
            FlavourProfileButton(navController)
            Spacer(modifier = Modifier.height(20.dp))
            ARexperienceButton()
            Spacer(modifier = Modifier.height(20.dp))
            StandardButton(
                onClick = {navController.navigate(Screen.FlavourScreen.route)  },
                text = "Flavours",
            )
        }
    }
}

@Composable
fun OnBoardingButton(navController: NavHostController) {
    StandardButton(
        onClick = { navController.navigate(Screen.OnboardingWelcomeScreen.route) },
        text = stringResource(id = R.string.buttonOnboarding)
    )
}

@Composable
fun FlavourProfileButton(navController: NavHostController) {
    StandardButton(
        onClick = { navController.navigate(Screen.SubscriptionScreen.route) },
        text = stringResource(id = R.string.buttonFlavourProfile)
    )
}

@Composable
fun StandardButton(
    modifier: Modifier = Modifier.padding(start = 25.dp, end = 25.dp),
    onClick: () -> Unit,
    text: String,
    withArrow: Boolean = false,
    darkMode: Boolean = false
) {
    val backgroundColor = if (darkMode) Slate else SoftWhite
    val textColor = if (darkMode) SoftWhite else Slate
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
    )
    {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            letterSpacing = 1.2.sp,
            fontFamily = FontFamily(Font(R.font.iqos_regular))
        )
        if (withArrow) {
            Image(
                painter = painterResource(id = R.drawable.arrowright),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 15.dp)
            )
        }
    }
}

@Composable
fun ARexperienceButton() {
    val context = LocalContext.current
    StandardButton(
        onClick = { showAR(context = context) },
        text = stringResource(id = R.string.buttonAR)
    )
}

/**
 * When using "com.google.ar.core" external app, the model files MUST be hosted online.
 * It will not work if models are in app assets.
 * Test model are in ar-models directory.
 */
private fun showAR(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW)
    val uri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
        .appendQueryParameter(
            "file",
            "http://www.appuploads.msvc.cloud.s3.amazonaws.com/pmi/iqos.glb"
        )
        .appendQueryParameter("mode", "ar_only")
        .build()
    intent.data = uri
    intent.`package` = "com.google.ar.core"
    context.startActivity(intent)
}

@Composable
@Preview
fun preview() {
    JourneySelectorScreen(navController = NavHostController(LocalContext.current))
}