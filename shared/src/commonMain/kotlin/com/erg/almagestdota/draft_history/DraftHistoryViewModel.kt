package com.erg.almagestdota.draft_history

import com.erg.almagestdota.base.BaseViewModel


class DraftHistoryViewModel : BaseViewModel<DraftHistoryViewState, DraftHistoryViewAction>() {
    init {
        args[]
    }

    override fun obtainAction(action: DraftHistoryViewAction) {
        when (action) {
            is DraftHistoryViewAction.OnBackPressed -> {

            }
            is DraftHistoryViewAction.OpenSomething -> {
                val screen = DraftHistoryScreen()
                navigate(screen) {
                    //todo result при переходе
                }
            }
            else -> Unit
        }
    }

    override fun defineFragmentState(): DraftHistoryViewState {
        return DraftHistoryViewState.DefaultState()
    }
}