package com.ar4uk.onlineshopcomputers.presentation.select

import androidx.lifecycle.ViewModel
import com.ar4uk.onlineshopcomputers.data.repository.AppsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart

class SelectViewModel: ViewModel() {
    private val repository = AppsRepository

    val state: Flow<State> = repository.checkList
        .filter { it.isNotEmpty() }
        .map { State.Content(checkList = it) as State  }
        .onStart { emit(State.Loading) }

    private fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }


}