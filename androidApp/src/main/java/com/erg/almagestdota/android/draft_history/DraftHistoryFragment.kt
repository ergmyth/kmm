package com.erg.almagestdota.android.draft_history

import androidx.activity.compose.BackHandler
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.erg.almagestdota.android.DraftCompose
import com.erg.almagestdota.android.base.navigateScreen
import com.erg.almagestdota.base.BackPressListener
import com.erg.almagestdota.base.ViewEvent
import com.erg.almagestdota.draft_history.DraftHistoryViewAction
import com.erg.almagestdota.draft_history.DraftHistoryViewModel
import com.erg.almagestdota.draft_history.DraftHistoryViewState
import dev.icerock.moko.mvvm.getViewModel
import kotlinx.coroutines.flow.onEach

class DraftHistoryFragment :
    BaseComposeFragment<DraftHistoryViewState, DraftHistoryViewAction, DraftHistoryViewModel>(),
    BackPressListener {

    override val viewModel = getViewModel { DraftHistoryViewModel() }

    @Composable
    override fun Content() {
        val viewState by viewModel.viewState.collectAsState()
        val loadingState by viewModel.loadingState.collectAsState()
        val viewEvent by viewModel.viewEvent.collectAsState(Unit)
        val navController = rememberNavController()
        when (viewEvent) {
            is ViewEvent.Navigation -> {
                navController.navigateScreen(viewEvent.screen)
            }
            is ViewEvent.AlmagestSnackbar -> {
                Snackbar() {
                    
                }
            }
            is ViewEvent.PopBackStack -> {
                navController.popBackStack()
            }
            else -> Unit
        }
        when (viewState) {
            is DraftHistoryViewState.DefaultState -> {
                //todo
            }

            else -> Unit
        }
        BackHandler() {

        }

        DraftCompose(navController = navController)
    }

    override fun onBackPressed() {
        viewModel.obtainAction(DraftHistoryViewAction.OnBackPressed)
    }
}