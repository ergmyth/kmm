package com.erg.almagestdota.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.erg.almagestdota.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PreviewMainActivity()
                }
            }
        }
    }
}

sealed class BottomScreens(val route: String, @StringRes val resourceId: Int) {
    object Draft : BottomScreens("Draft", R.string.bnv_draft)
    object Live : BottomScreens("Live", R.string.bnv_live)
    object Stats : BottomScreens("Statistics", R.string.bnv_stats)
    object Settings : BottomScreens("Settings", R.string.bnv_settings)
}

@Composable
fun MainActivityCompose() {
    val navController = rememberNavController()

    BaseCompose(
        navController
    )
}

@Preview
@Composable
fun PreviewMainActivity() {
    MainActivityCompose()
}
