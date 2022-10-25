package com.lnight.core.util

sealed interface UiEvent {
    object Success: UiEvent
    object NavigateUp: UiEvent
    data class ShowSnackbar(val message: UiText): UiEvent
}