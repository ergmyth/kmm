package com.erg.almagestdota.draft_history

import com.erg.almagestdota.base.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach

class DraftHistoryScreen constructor(

) : Screen<String>(
    route = "",

) {
    enum class Args {
        ARG_1;
        companion object {
            fun getArg1(data: HashMap<String, Any?>): String? {
                return data[ARG_1.name] as? String
            }
        }
    }

    enum class Result {
        RESULT_1;
        companion object {
            private val ARG_1_KEY = "ARG_1_KEY"
            fun getResult(data: HashMap<String, Any?>): String? {
                return data[Args.ARG_1.name] as? String
            }
        }
    }
}