package com.erg.almagestdota.base

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : ViewState, A : ViewAction> constructor(
    private val navigationStack: NavigationStack
) : ViewModel() {

    private val currentScreen = navigationStack.getLast()
    protected val args = currentScreen.data

    protected val _viewState: MutableStateFlow<S> by lazy { MutableStateFlow(defineFragmentState()) }
    val viewState by lazy { _viewState.asStateFlow() }

    protected val _loadingState by lazy { MutableStateFlow<Loading>(Loading.Disabled) }
    val loadingState by lazy { _loadingState.asStateFlow() }

    protected val _viewEvent = MutableResultChannel()
    val viewEvent: ResultFlow = _viewEvent

    abstract fun obtainAction(action: A)

    protected abstract fun defineFragmentState(): S

    protected fun exit(map: HashMap<String, Any?>) {
        currentScreen.setResult(map)
        emit(ViewEvent.PopBackStack)
    }

    protected fun navigate(screen: Screen<*>, onResult: (HashMap<String, Any?>) -> Unit) {
        screen.result.filterNotNull().onEach {
            navigationStack.pop()
            onResult.invoke(it)
        }.launchIn(viewModelScope)
        navigationStack.push(screen)
        emit(ViewEvent.Navigation(screen))
    }

    private fun emit(event: ViewEvent) {
        viewModelScope.launch { _viewEvent.send(event) }
    }
}

//todo стек для каждого меню элемента
class NavigationStack() {
    private val stack: ArrayDeque<Screen<*>> = ArrayDeque(listOf())

    fun push(screen: Screen<*>) {
        stack.addLast(screen)
    }

    fun pop() {
        stack.removeLast()
    }

    fun getLast(): Screen<*> {
        return stack.last()
    }
}