package com.erg.almagestdota.android.base

import android.os.Handler
import android.os.Looper
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.erg.almagestdota.base.Screen
import java.lang.Exception
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun Fragment.navController(): NavControllerDelegate {
    return NavControllerDelegate(this)
}

class NavControllerDelegate @PublishedApi internal constructor(
    private val fragment: Fragment
) : ReadOnlyProperty<Any?, NavController> {

    private val handler = Handler(Looper.getMainLooper())
    private val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            handler.post {
                if (!fragment.isAdded) return@post
                obtainNavController()
            }
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            handler.post { navController = null }
        }
    }

    private var navController: NavController? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
            lifecycleOwner.lifecycle.addObserver(observer)
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): NavController =
        navController ?: obtainNavController()

    private fun obtainNavController(): NavController {
        return fragment.findNavController()
            .also { navController = it }
    }
}

fun NavController.navigateScreen(
    screen: Screen<*>,
    extras: Navigator.Extras? = null,
    navOptions: NavOptions? = null
) {
    try {
        when (screen.route) {
            is String -> navigate((screen.route as String).toUri(), navOptions, extras)
            is NavDirections -> {
                (screen.route as NavDirections).let { direction ->
                    currentDestination?.getAction(direction.actionId)?.let {
                        navigate(direction, navOptions)
                    }
                }
            }

            is Int -> navigate(screen.route as Int, null, navOptions, extras)
            else -> throw IllegalArgumentException("Unsupported route type (${screen.route})")
        }
    } catch (e: Exception) {

    }
}
