package com.ar4uk.onlineshopcomputers.data.mapper

import com.ar4uk.onlineshopcomputers.domain.model.App

class AppMapper {
    fun mapStringToApp(checkNames: List<String>): List<App> {
        val result = mutableListOf<App>()

        for((index, checkName) in checkNames.withIndex()) {
            result.add(
                App(
                    id = index.toString(),
                    name = checkName,
                    check = false
                )
            )
        }
        return result
    }
}