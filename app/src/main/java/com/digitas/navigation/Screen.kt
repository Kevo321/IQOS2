package com.digitas.navigation

sealed class Screen(val route: String) {
    object Splash:Screen("splash_screen")
    object SelectionScreen:Screen("Selection_screen")
    object OnboardingWelcomeScreen:Screen("OnBoarding_WelcomeScreen")
    object OnboardingJourneyScreen:Screen("Onboarding_JourneyScreen")
    object OnboardingScreen_1:Screen("OnboardingScreen_1")
    object OnboardingScreen_2:Screen("OnboardingScreen_2")
    object OnboardingScreen_3:Screen("OnboardingScreen_3")
    object DevicePairingSetupScreen: Screen("DevicePairingSetupScreen")
    object DeviceConnectScreen1:Screen("DeviceConnectScreen1")
    object DeviceConnectScreen2:Screen("DeviceConnectScreen2")
    object DevicePairSuccessfulScreen:Screen("DevicePairSuccessfulScreen")
    object FlavourScreen:Screen("FlavourScreen")
    object SubscriptionScreen:Screen("SubscriptionScreen")
}