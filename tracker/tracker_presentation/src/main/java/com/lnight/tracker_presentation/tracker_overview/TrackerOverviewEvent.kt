package com.lnight.tracker_presentation.tracker_overview

import com.lnight.tracker_domain.model.TrackedFood

sealed interface TrackerOverviewEvent {
    object OnNextDayClick:  TrackerOverviewEvent
    object OnDayChanged:  TrackerOverviewEvent
    object OnPreviousDayClick: TrackerOverviewEvent
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvent
}