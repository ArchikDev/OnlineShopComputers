package com.ar4uk.onlineshopcomputers.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar4uk.onlineshopcomputers.data.repository.AppsRepository
import com.ar4uk.onlineshopcomputers.domain.model.App
import com.ar4uk.onlineshopcomputers.presentation.select.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val repository = AppsRepository

    val checkList: Flow<Int> = repository.checkList
        .map {
            val appChecked = it.filter { it1 -> it1.check }

            appChecked.size
        }


}