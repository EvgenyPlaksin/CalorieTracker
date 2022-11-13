package com.lnight.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnight.core.domain.preferences.Preferences
import com.lnight.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
): ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set

    private var getFoodsForDateJob: Job? = null

    var isToday = true

    init {
        refreshFoods()
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when(event) {
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFoods()
                }
            }
            is TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                isToday = state.date.dayOfYear == LocalDate.now().dayOfYear
                refreshFoods()
            }
            is TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                isToday = state.date.dayOfYear == LocalDate.now().dayOfYear
                refreshFoods()
            }
            is TrackerOverviewEvent.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if(it.name == event.meal.name) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }
            TrackerOverviewEvent.OnDayChanged ->  {
                state = state.copy(date = LocalDate.now())
                refreshFoods()
            }
        }
    }

    private fun refreshFoods() {
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases
            .getFoodsForDate(state.date)
            .onEach { foods ->
                val nutrientsResult = trackerUseCases.calculateMealNutrients(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProtein = nutrientsResult.totalProtein,
                    totalFat = nutrientsResult.totalFat,
                    totalCalories = nutrientsResult.totalCalories,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    trackedFoods = foods,
                    meals = state.meals.map {
                        val nutrientsForMeal =
                            nutrientsResult.mealNutrients[it.mealType]
                                ?: return@map it.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0
                                )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat = nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories
                        )
                    }
                )
            }.launchIn(viewModelScope)
        if(state.trackedFoods.isEmpty()) {
            val nutrientsResult = trackerUseCases.calculateMealNutrients(emptyList())
            state = state.copy(
                totalCarbs = nutrientsResult.totalCarbs,
                totalProtein = nutrientsResult.totalProtein,
                totalFat = nutrientsResult.totalFat,
                totalCalories = nutrientsResult.totalCalories,
                carbsGoal = nutrientsResult.carbsGoal,
                proteinGoal = nutrientsResult.proteinGoal,
                fatGoal = nutrientsResult.fatGoal,
                caloriesGoal = nutrientsResult.caloriesGoal,
                meals = state.meals.map {
                    val nutrientsForMeal =
                        nutrientsResult.mealNutrients[it.mealType]
                            ?: return@map it.copy(
                                carbs = 0,
                                protein = 0,
                                fat = 0,
                                calories = 0
                            )
                    it.copy(
                        carbs = nutrientsForMeal.carbs,
                        protein = nutrientsForMeal.protein,
                        fat = nutrientsForMeal.fat,
                        calories = nutrientsForMeal.calories
                    )
                }
            )
        }
    }
}