package com.digitas.iqos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.digitas.iqos.ui.theme.IQOSTheme
import com.digitas.navigation.Screen
import com.digitas.navigation.SetupNavGraph
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks


class MainActivity : ComponentActivity() {

    private lateinit var navigationController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IQOSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    navigationController = rememberNavController()
                    SetupNavGraph(navController = navigationController)
                }
            }
        }

        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(
                this
            ) { pendingDynamicLinkData ->
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }
                var path = deepLink?.getQueryParameter("path")
                var token = deepLink?.getQueryParameter("token")
                if (path == DEEPLINK_ONBOARDING) {
                    navigationController.navigate(Screen.OnboardingWelcomeScreen.route)
                }
            }
            .addOnFailureListener(
                this
            ) { e -> Log.w("Deeplink:", "getDynamicLink:onFailure", e) }
    }

    companion object {
        const val DEEPLINK_ONBOARDING = "onboarding"
    }
}

