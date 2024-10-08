package com.carry1st.ecommerce.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.carry1st.ecommerce.ui.component.BottomAppNavigation
import com.carry1st.ecommerce.ui.navigation.Navigation

@Composable
fun App() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppNavigation(navHostController = navController)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Navigation(
            navHostController = navController,
            snackbarHostState = snackbarHostState
        )
    }
}