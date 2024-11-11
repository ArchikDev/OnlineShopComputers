package com.ar4uk.onlineshopcomputers.data.repository

import com.ar4uk.onlineshopcomputers.data.mapper.AppMapper
import com.ar4uk.onlineshopcomputers.domain.model.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

object AppsRepository {

    private val checkNames = listOf("check1", "check2", "check3", "check4", "check5")
    private val mapper = AppMapper()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    val checkList: Flow<List<App>> = flow {
        delay(3000)
        emit(mapper.mapStringToApp(checkNames))
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )
}