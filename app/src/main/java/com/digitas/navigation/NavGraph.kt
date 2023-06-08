package com.digitas.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.digitas.Screens.*
import com.digitas.Screens.devicepairing.DeviceConnectScreen1
import com.digitas.Screens.devicepairing.DeviceConnectScreen2
import com.digitas.Screens.devicepairing.DevicePairSetupScreen
import com.digitas.Screens.devicepairing.DevicePairSuccessfulScreen
import com.digitas.Screens.onboarding.OnboardingScreen_1
import com.digitas.Screens.onboarding.OnboardingScreen_2
import com.digitas.Screens.onboarding.OnboardingScreen_3
import com.digitas.Screens.subscription.SubscriptionScreen


@Composable
fun  SetupNavGraph(navController: NavHostController) {
   NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
               VideoSplashScreen(navController = navController)
        }
        composable(route = Screen.SelectionScreen.route) {
            EnterAnimation {
                        JourneySelectorScreen(navController)
            }
        }
       composable(route = Screen.OnboardingWelcomeScreen.route) {
            EnterAnimation {
                OnBoardingWelcomeScreen(navController)
            }
       }
       composable(route = Screen.OnboardingJourneyScreen.route) {
           EnterAnimation {
               OnboardingScreen_1(navController = navController)
           }
       }
       composable(route = Screen.OnboardingScreen_2.route){
           EnterAnimation {
               OnboardingScreen_2(navController = navController)
           }
       }
       composable(route = Screen.OnboardingScreen_3.route){
           EnterAnimation {
               OnboardingScreen_3(navController = navController)
           }
       }
       composable(route = Screen.DevicePairingSetupScreen.route){
           EnterAnimation {
               DevicePairSetupScreen(navController = navController)
           }
       }
       composable(route = Screen.DeviceConnectScreen1.route){
           EnterAnimation {
               DeviceConnectScreen1(navController = navController)
           }
       }
       composable(route = Screen.DeviceConnectScreen2.route){
           EnterAnimation {
               DeviceConnectScreen2(navController = navController)
          }
       }
       composable(route = Screen.DevicePairSuccessfulScreen.route){
           EnterAnimation {
               DevicePairSuccessfulScreen(navController = navController)
           }
       }
       composable(route = Screen.FlavourScreen.route) {
           EnterAnimation {
               FlavourBottomSheet(navController)
           }
       }
       composable(route = Screen.SubscriptionScreen.route){
           EnterAnimation {
               SubscriptionScreen(navController = navController)
           }
       }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { 65 }
        ) + fadeIn(initialAlpha = .2f),
        exit = slideOutHorizontally() + shrinkVertically(),
        content = content,
        initiallyVisible = false
    )
}