package com.lnight.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lnight.calorytracker.ui.theme.CaloryTrackerTheme
import com.lnight.onboarding_presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                WelcomeScreen()
            }
        }
    }
}