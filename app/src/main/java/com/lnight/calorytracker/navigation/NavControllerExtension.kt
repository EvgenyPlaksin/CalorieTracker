package com.lnight.calorytracker.navigation

import androidx.navigation.NavController
import com.lnight.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}