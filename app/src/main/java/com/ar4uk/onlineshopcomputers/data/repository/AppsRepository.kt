package com.ar4uk.onlineshopcomputers.data.repository

import com.ar4uk.onlineshopcomputers.data.mapper.AppMapper
import com.ar4uk.onlineshopcomputers.domain.model.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

object AppsRepository {

    private val checkNames = listOf("check1", "check2", "check3", "check4", "check5")
    private var checkListApp = mutableListOf<App>()
    private val mapper = AppMapper()

    private val updateEvent = MutableSharedFlow<Unit>()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    val checkList: StateFlow<List<App>> = flow {
        delay(3000)

        checkListApp.addAll(mapper.mapStringToApp(checkNames))

        emit(checkListApp.toList())

        updateEvent.collect {
            emit(checkListApp.toList())
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )

    suspend fun setCheckApp(idApp: String, isCheck: Boolean = false) {
        val tempCheckListApp = checkListApp.toList()

        checkListApp.clear()

        checkListApp.addAll(tempCheckListApp.map { check ->
            if (check.id == idApp) check.copy(
                check = isCheck
            ) else check
        })

        updateEvent.emit(Unit)
    }
}