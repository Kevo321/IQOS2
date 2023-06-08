package com.digitas.Screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
import androidx.navigation.NavHostController
import com.digitas.iqos.R
import com.digitas.iqos.ui.theme.Slate
import com.digitas.iqos.ui.theme.SoftWhite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun FlavourBottomSheet(navController: NavHostController) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var flavour by remember { mutableStateOf(TropicalFlavour) }
    ModalBottomSheetLayout(
        sheetState = state,
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            Card(scope, state, flavour)
        }
    ) {
        BackGroundApp {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Spacer(Modifier.height(20.dp))
                StandardButton(onClick = {
                    flavour = TropicalFlavour
                    scope.launch { state.expandFull() }
                }, text = "Tropical Swift")
                Spacer(Modifier.height(20.dp))
                StandardButton(onClick = {
                    flavour = PurpleFlavour
                    scope.launch { state.expandFull() }
                }, text = "Purple Wave")
                Spacer(Modifier.height(20.dp))
                StandardButton(
                    onClick = {
                        flavour = BrightFlavour
                        scope.launch { state.expandFull() }
                    },
                    text = "Bright Vibe"
                )
                Spacer(Modifier.height(20.dp))
                StandardButton(
                    onClick = {
                        flavour = TidalFlavour
                        scope.launch { state.expandFull() }
                    },
                    text = "Tidal Pearl"
                )
                Spacer(Modifier.height(20.dp))
                StandardButton(
                    onClick = {
                        flavour = OasisFlavour
                        scope.launch { state.expandFull() }
                    },
                    text = "Oasis Pearl"
                )
                Spacer(Modifier.height(20.dp))
                StandardButton(
                    onClick = {
                        flavour = ArborFlavour
                        scope.launch { state.expandFull() }
                    },
                    text = "Arbor Pearl"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private suspend fun ModalBottomSheetState.expandFull() {
    animateTo(ModalBottomSheetValue.Expanded, anim = tween(1000))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Card(scope: CoroutineScope, state: ModalBottomSheetState, flavour: Flavour) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .height(133.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = flavour.headerImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(133.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
            Image(
                painter = painterResource(id = R.drawable.close_button),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .height(59.dp)
                    .width(59.dp)
                    .padding(end = 24.dp, top = 24.dp)
                    .clickable(
                        enabled = true,
                        onClick = { scope.launch { state.hide() } }
                    )
            )
        }
        val textColor = if (flavour.darkMode) Slate else SoftWhite
        Box {
            Column(
                modifier = Modifier
                    .background(color = flavour.backgroundColor)
            ) {
                Text(
                    text = stringResource(id = flavour.title),
                    color = textColor,
                    textAlign = TextAlign.Start,
                    lineHeight = 40.sp,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.iqosbold)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 4.dp, top = 20.dp)
                )
                Text(
                    text = stringResource(id = flavour.subtitle),
                    color = textColor,
                    textAlign = TextAlign.Start,
                    lineHeight = 28.sp,
                    letterSpacing = 0.2.sp,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
                )
                Text(
                    text = stringResource(id = flavour.description),
                    color = textColor,
                    textAlign = TextAlign.Start,
                    lineHeight = 20.sp,
                    letterSpacing = 0.4.sp,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 26.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 26.dp)
                ) {
                    val aroma = stringResource(id = flavour.aromaNote)
                    Text(
                        text = "Aroma note: $aroma",
                        color = textColor,
                        textAlign = TextAlign.Start,
                        lineHeight = 20.sp,
                        letterSpacing = 0.4.sp,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.iqos_regular)),
                        modifier = Modifier
                            .padding(start = 24.dp, end = 10.dp)
                    )
                    Image(
                        painter = painterResource(id = flavour.aromaIcon),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(textColor),
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                    )
                }
                FlavourMeter("Cooling", flavour.cooling, textColor)
                FlavourMeter("Intensity", flavour.intensity, textColor)
                FlavourMeter("Body", flavour.body, textColor)
                FlavourMeter("Aroma", flavour.aroma, textColor)
                StandardButton(
                    Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 40.dp),
                    onClick = { },
                    text = "Add to subscription",
                    darkMode = flavour.darkMode
                )
            }
            var heartTint by remember { mutableStateOf(SoftWhite) }
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(heartTint),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .height(66.dp)
                    .width(66.dp)
                    .padding(end = 24.dp, top = 24.dp)
                    .clickable(
                        enabled = true,
                        onClick = {
                            flavour.like = !flavour.like
                            heartTint = if (flavour.like) {
                                if (flavour.darkMode) SoftWhite else Slate
                            } else {
                                if (flavour.darkMode) Slate else SoftWhite
                            }
                        }
                    )
            )
        }
    }
}


@Composable
fun FlavourMeter(name: String, value: Int, textColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Text(
            text = name,
            color = textColor,
            textAlign = TextAlign.Start,
            lineHeight = 20.sp,
            letterSpacing = 0.4.sp,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.iqos_regular)),
            modifier = Modifier
                .padding(start = 24.dp, end = 52.dp)
                .width(75.dp)
        )
        Image(
            painter = painterResource(id = getFlavourBarImage(value)),
            contentDescription = "",
            colorFilter = ColorFilter.tint(textColor),
            modifier = Modifier
                .height(6.dp)
                .width(96.dp)
        )
    }
}

fun getFlavourBarImage(value: Int): Int {
    return when (value) {
        0 -> R.drawable.flavour_bar_2
        1 -> R.drawable.flavour_bar_2
        2 -> R.drawable.flavour_bar_2
        3 -> R.drawable.flavour_bar_3
        4 -> R.drawable.flavour_bar_4
        5 -> R.drawable.flavour_bar_5
        else -> R.drawable.flavour_bar_2
    }
}

@Composable
@Preview
fun PreviewConsumableSheet() {
    FlavourBottomSheet(navController = NavHostController(LocalContext.current))
}