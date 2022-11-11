package com.lnight.tracker_presentation.search

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = true,
    val isSearching: Boolean = false,
    val page: Int = 1,
    val endReached: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)
