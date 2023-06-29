package com.erg.almagestdota.draft_history

import com.erg.almagestdota.base.BaseViewModel
import com.erg.almagestdota.base.Loading
import com.erg.almagestdota.base.MutableResultChannel
import com.erg.almagestdota.base.ViewAction
import com.erg.almagestdota.base.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class DraftHistoryViewState() : ViewState() {
    class DefaultState() : DraftHistoryViewState()
}