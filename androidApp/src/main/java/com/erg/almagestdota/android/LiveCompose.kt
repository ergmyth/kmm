package com.erg.almagestdota.android

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun LiveCompose(navController: NavHostController) {
    Button(onClick = {}) {
        Text(text = "live")
    }
}
