package com.erg.almagestdota.base

class PendingExecution(
    val requestKey: String? = null,
    val onDismiss: (() -> Unit)? = null,
)