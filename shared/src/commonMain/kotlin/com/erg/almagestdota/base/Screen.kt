package com.erg.almagestdota.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Screen<T>(
    val route: T,
    val data: HashMap<String, Any?> = hashMapOf()
) {
    private val _result by lazy { MutableStateFlow<HashMap<String, Any?>?>(null) }
    val result by lazy { _result.asStateFlow() }

    fun setResult(map: HashMap<String, Any?>) {
        _result.value = map
    }
}