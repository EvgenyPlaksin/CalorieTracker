package com.lnight.core.util

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    object NavigateUp: UiEvent()
    data class ShowShackbar(val message: UiText): UiEvent()
}