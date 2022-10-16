package com.lnight.tracker_domain.use_case

import com.lnight.tracker_domain.model.TrackedFood
import com.lnight.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
     suspend operator fun invoke(trackedFood: TrackedFood) {
       repository.deleteTrackerFood(trackedFood)
    }
}