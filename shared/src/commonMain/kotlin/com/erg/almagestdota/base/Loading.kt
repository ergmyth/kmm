package com.erg.almagestdota.base

sealed class Loading {
    object Enabled : Loading()
    object Disabled : Loading()
}