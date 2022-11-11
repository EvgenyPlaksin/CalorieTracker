package com.lnight.tracker_presentation.search.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}