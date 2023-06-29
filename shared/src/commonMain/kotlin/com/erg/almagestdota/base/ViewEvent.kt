package com.erg.almagestdota.base

abstract class ViewEvent {
    class AlmagestSnackbar(
        val message: String? = null,
        val status: NotificationStatus,
        val delayMillis: Long = 0L,
    ) : ViewEvent()

    class Navigation(val screen: Screen<*>) : ViewEvent()
    object PopBackStack : ViewEvent()
}