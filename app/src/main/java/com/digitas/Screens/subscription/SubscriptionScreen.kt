package com.digitas.Screens.subscription

import android.annotation.SuppressLint
import android.view.*
import android.widget.TextView
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.digitas.iqos.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.res.ResourcesCompat
import com.digitas.Screens.*
import com.digitas.iqos.ui.theme.Slate
import com.digitas.iqos.ui.theme.tabbedColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SubscriptionScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val animate = remember { mutableStateOf(1000) }
    val mutablePieData = remember { mutableStateOf(PieData()) }
    val navigationVariable = remember { mutableStateOf("") }
    val mutablePieDataSet =
        remember { mutableStateOf(PieDataSet(DataUtil.firstSectionEntries, "Area")) }
    val animateInner = if (animate.value == 0) 1800 else 0
    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var flavour = remember { mutableStateOf(TropicalFlavour) }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            com.digitas.Screens.Card(scope, state, flavour.value)
        }
    ) {
        BackGroundApp {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Slate)
                    .verticalScroll(rememberScrollState())
            ) {


                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 5.dp)
                ) {
                    val (Title, text, pieLayout, tabbedView, card, button, column) = createRefs()
                    Text(
                        modifier = Modifier
                            .constrainAs(Title) {
                                top.linkTo(parent.top, 30.dp)
                            },
                        text = stringResource(id = R.string.subscriptionTitle), color = Color.White,
                        lineHeight = 40.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.iqosbold)),
                    )
                    Text(text = stringResource(id = R.string.subscriptionSubTxt),
                        color = Color.White,
                        lineHeight = 40.sp,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.iqosbold)),
                        modifier = Modifier
                            .constrainAs(text) {
                                top.linkTo(Title.bottom)
                            })
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(column) {
                            top.linkTo(text.bottom)
                        }) {
                        TabbedView(
                            modifier = Modifier
                                .padding(end = 10.dp, top = 15.dp)
                                .fillMaxWidth()
                        )
                        InitializePieChart(
                            modifier = Modifier,
                            animate = animate,
                            mutablePieData = mutablePieData,
                            mutablePieDataSet = mutablePieDataSet,
                            scope = scope,
                            flavour = flavour,
                            state = state
                        )
                        Card(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = extraPadding.coerceAtLeast(0.dp))
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = tabbedColor),
                        ) {
                            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                                val (title, paymentAmountTxt, progressBar, daysLeft, nextPaymentTxt, expandBttn, expandableContent) = createRefs()
                                Text(text = stringResource(id = R.string.subscriptionIQOS12),
                                    color = Color.White,
                                    lineHeight = 40.sp,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.iqosbold)),
                                    modifier = Modifier
                                        .padding(top = 15.dp, start = 15.dp)
                                        .constrainAs(title) {
                                            top.linkTo(parent.top)
                                        })
                                Text(text = stringResource(id = R.string.subscriptionCost),
                                    color = Color.White,
                                    lineHeight = 40.sp,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                    modifier = Modifier
                                        .padding(start = 50.dp, top = 25.dp)
                                        .constrainAs(paymentAmountTxt) {
                                            start.linkTo(title.end)
                                        })
                                Text(text = stringResource(id = R.string.subscriptionDaysLeft),
                                    color = Color.White,
                                    lineHeight = 40.sp,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                    modifier = Modifier
                                        .padding(start = 15.dp, top = 10.dp)
                                        .constrainAs(daysLeft) {
                                            top.linkTo(title.bottom)
                                        })
                                Text(text = stringResource(id = R.string.subscriptionNextPayment),
                                    color = Color.White,
                                    lineHeight = 40.sp,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                    modifier = Modifier
                                        .padding(start = 80.dp, top = 68.dp)
                                        .constrainAs(nextPaymentTxt) {
                                            start.linkTo(daysLeft.end)
                                        })
                                SubscriptionIndicator(indicatorProgress = 0f,
                                    modifier = Modifier
                                        .padding(top = 20.dp, start = 10.dp)
                                        .width(295.dp)
                                        .constrainAs(progressBar) {
                                            top.linkTo(nextPaymentTxt.bottom)
                                        })
                                Column(modifier = Modifier
                                    .constrainAs(expandableContent) {
                                        top.linkTo(progressBar.bottom)
                                    }) {
                                    if (expanded) {
                                        Row(modifier = Modifier.padding(top = 20.dp)) {
                                            Image(
                                                painter = painterResource(id = R.drawable.tickcircle),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .padding(start = 15.dp)
                                            )
                                            Text(
                                                text = stringResource(id = R.string.subscriptionInfo1),
                                                color = Color.White,
                                                lineHeight = 20.sp,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                                modifier = Modifier.padding(start = 5.dp)
                                            )


                                        }
                                        Row(
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                start = 15.dp
                                            )
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.tickcircle),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .padding(start = 1.dp)
                                            )

                                            Text(
                                                text = stringResource(id = R.string.subscriptionInfo2),
                                                color = Color.White,
                                                lineHeight = 20.sp,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                                modifier = Modifier.padding(start = 5.dp)
                                            )

                                        }
                                        Row(
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                start = 15.dp
                                            )
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.tickcircle),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .padding(start = 1.dp)
                                            )
                                            Text(
                                                text = stringResource(id = R.string.subscriptionInfo3),
                                                color = Color.White,
                                                lineHeight = 20.sp,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                                modifier = Modifier.padding(start = 5.dp)
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                start = 15.dp
                                            )
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.tickcircle),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .padding(start = 1.dp)
                                            )
                                            Text(
                                                text = stringResource(id = R.string.subscriptionInfo4),
                                                color = Color.White,
                                                lineHeight = 20.sp,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                                modifier = Modifier.padding(start = 5.dp)
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                start = 15.dp
                                            )
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.tickcircle),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .align(Alignment.CenterVertically)
                                                    .padding(start = 1.dp)
                                            )
                                            Text(
                                                text = stringResource(id = R.string.subscriptionInfo5),
                                                color = Color.White,
                                                lineHeight = 20.sp,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = FontFamily(Font(R.font.iqos_regular)),
                                                modifier = Modifier.padding(start = 5.dp)
                                            )
                                        }

                                    }
                                }
                                Button(
                                    onClick = { expanded = expanded == false },
                                    colors = ButtonDefaults.buttonColors(Color.White),
                                    modifier = Modifier//.padding(start =130.dp )
                                        .height(4.dp)
                                        .width(60.dp)
                                        .constrainAs(expandBttn) {
                                            top.linkTo(expandableContent.bottom, 35.dp)
                                            bottom.linkTo(parent.bottom, 35.dp)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        }) {}

                            }

                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        ManageSubscriptionButton(
                            navController = navController,
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (navigationVariable.value.length > 0) {

//                    bottom:BottomAppBarDefaults = flavourCardBottom
//                    bottom.show()
                            //navigate
                        }
                    }

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InitializePieChart(
    modifier: Modifier,
    animate: MutableState<Int>,
    mutablePieData: MutableState<PieData>,
    mutablePieDataSet: MutableState<PieDataSet>,
    scope: CoroutineScope,
    flavour: MutableState<Flavour>,
    state: ModalBottomSheetState
) {
    val context = LocalContext.current
    val view = LayoutInflater.from(context).inflate(R.layout.pie_chart, null, false)
    val outerPieChart = view.findViewById<PieChart>(R.id.pie_chart_outer)
    val innerPieChart = view.findViewById<PieChart>(R.id.pie_chart_inner)
    val parentView: ViewGroup = outerPieChart.parent as ViewGroup
    val innerText = view.findViewById<TextView>(R.id.titleText)

    DisplayInnerCircle(
        innerPie = innerPieChart,
        outerPieChart = outerPieChart,
        view = view,
        modifier = modifier,
        animate = animate,
        mutablePieData = mutablePieData,
        mutablePieDataSet = mutablePieDataSet,
        innerTextView = innerText,
        scope = scope,
        flavour = flavour,
        state = state
    )

//    innerText.setOnClickListener {
//        val animator = ObjectAnimator.ofFloat(outerPieChart, View.ALPHA, 1f, 0f)
//        animator.duration = 1000
//        animator.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator) {
//                parentView.removeView(outerPieChart)
//            }
//        })
//        animator.start()
//    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayInnerCircle(
    innerPie: PieChart,
    outerPieChart: PieChart,
    view: View,
    modifier: Modifier,
    animate: MutableState<Int>,
    mutablePieData: MutableState<PieData>,
    mutablePieDataSet: MutableState<PieDataSet>,
    innerTextView: TextView,
    scope: CoroutineScope,
    flavour: MutableState<Flavour>,
    state: ModalBottomSheetState
) {
    mutablePieDataSet.value.colors = DataUtil.aromaNotesColors
    mutablePieData.value.dataSet = mutablePieDataSet.value
    innerPie.data = mutablePieData.value
    innerPie.animateY(1300)
    innerPie.data.setDrawValues(false)
    innerPie.setDrawEntryLabels(false)
    innerPie.setDrawMarkers(false)
    innerPie.setHoleColor(Slate.toArgb())
    innerPie.setCenterTextColor(view.resources.getColor(R.color.white, null))
    innerPie.description.isEnabled = false
    innerPie.legend.isEnabled = false
    innerPie.transparentCircleRadius = 0f
    innerTextView.text = "Aroma\n" + "Notes"
    innerTextView.typeface = ResourcesCompat.getFont(view.context, R.font.iqosbold)

    innerPie.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            val index = h?.x?.toInt()
            val updatedColors = IntArray(DataUtil.aromaNotesColors.size)
            val unselectedColors = android.graphics.Color.parseColor("#3C3B3D")
            for (i in DataUtil.aromaNotesColors.indices) {
                if (i != index) {
                    updatedColors[i] = unselectedColors
                } else {
                    updatedColors[i] = DataUtil.aromaNotesColors[i]
                }
            }
            mutablePieDataSet.value.colors = updatedColors.toList()
            val pieEntry = e as PieEntry
            when (pieEntry.label) {
                "StoneFruit" -> {
                    animate.value = 0
                    displayOutterCircle(
                        DataUtil.stoneFruitCombination, outerPieChart, scope,
                        flavour,
                        state, DataUtil.stoneFruitEntries
                    )
                    innerTextView.text = "Stone Fruit \n ${"78%"}"

                }
                "Menthol" -> {
                    animate.value = 0
                    displayOutterCircle(
                        DataUtil.colorCombinations, outerPieChart, scope,
                        flavour,
                        state, DataUtil.mentholEntries
                    )
                    innerTextView.text = "Menthol \n ${"22%"}"
                }
            }
        }

        override fun onNothingSelected() {}
    })
    AndroidView(
        factory = { context ->
            view
        },
        update = { view ->
        }, modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
fun displayOutterCircle(
    colorList: List<Int>, pieChart: PieChart, scope: CoroutineScope,
    flavour: MutableState<Flavour>,
    state: ModalBottomSheetState, listOfPieEntry: List<PieEntry>
) {
    val dataSet = PieDataSet(listOfPieEntry, "Section")
    val data = PieData(dataSet)
    pieChart.data = data
    dataSet.colors = colorList
    pieChart.animateY(1200)
    pieChart.data.setDrawValues(false)
    pieChart.setDrawEntryLabels(true) // toggle text
    pieChart.setEntryLabelTypeface(ResourcesCompat.getFont(pieChart.context, R.font.iqosbold))
    pieChart.setDrawCenterText(false)
    pieChart.setDrawMarkers(false)
    pieChart.setEntryLabelColor(pieChart.resources.getColor(R.color.white, null))
    pieChart.setCenterTextColor(pieChart.resources.getColor(R.color.white, null))
    pieChart.holeRadius = 70f
    pieChart.setHoleColor(Slate.toArgb())
    pieChart.description.isEnabled = false
    pieChart.legend.isEnabled = false
    pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            val pieEntry = e as PieEntry
            when (pieEntry.label) {
                "Tropical Swift" -> {
                    flavour.value = TropicalFlavour
                    scope.launch { state.expandFull() }
                }

                "Bright Vibe" -> {
                    flavour.value = BrightFlavour
                    scope.launch { state.expandFull() }
                }

                "Purple Wave" -> {
                    flavour.value = PurpleFlavour
                    scope.launch { state.expandFull() }
                }

                "Tidal pearl" -> {
                    flavour.value = TidalFlavour
                    scope.launch { state.expandFull() }
                }

                "Oasis Pearl" -> {
                    flavour.value = OasisFlavour
                    scope.launch { state.expandFull() }
                }

                "Arbor Pearl" -> {
                    flavour.value = ArborFlavour
                    scope.launch { state.expandFull() }
                }
            }
        }

        override fun onNothingSelected() {}
    })
}

@Composable
fun TabbedView(modifier: Modifier = Modifier) {
    val tabs = listOf(
        "Terea" to ::FirstTabContent,
        "HEETS" to ::SecondTabContent,
    )
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(modifier = modifier) {
        TabRow(selectedTabIndex = selectedTabIndex,
            modifier = modifier,
            tabbedColor, indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(5.dp)
                        .fillMaxWidth()
                        .background(Color.Cyan)
                )
            }, divider = {}
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = {
                        if (selectedTabIndex == index) {
                            Text(
                                tab.first, modifier = Modifier, color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.iqosbold))
                            )
                        } else {
                            Text(
                                tab.first, modifier = Modifier, color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.iqos_regular))
                            )
                        }

                    },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                )
            }
        }
        tabs[selectedTabIndex].second()
    }
}


fun FirstTabContent() {}

fun SecondTabContent() {}

@Composable
fun ManageSubscriptionButton(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            Color.White
        ),
        modifier = Modifier
            .height(60.dp)
            .width(325.dp)
    )
    {
        Text(
            text = stringResource(id = R.string.buttonmanageSubscription), color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.iqos_regular))
        )
    }
}

@Composable
fun SubscriptionIndicator(modifier: Modifier = Modifier, indicatorProgress: Float) {
    var progress by remember { mutableStateOf(indicatorProgress) }
    val progressAnimDuration = 4000
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )
    LinearProgressIndicator(
        modifier = modifier
            .height(5.dp)
            .background(Color.Gray)
            .clip(RoundedCornerShape(20.dp))
            .width(120.dp), // Rounded edges
        progress = progressAnimation,
        color = Color.Cyan
    )
    LaunchedEffect(indicatorProgress) {
        progress = .6f
    }

}

@OptIn(ExperimentalMaterialApi::class)
private suspend fun ModalBottomSheetState.expandFull() {
    animateTo(ModalBottomSheetValue.Expanded, anim = tween(1000))
}


@Composable
@Preview
fun UiCheckSubscriptionScreen() {
    SubscriptionScreen(navController = NavHostController(LocalContext.current))
}