package com.carry1st.ecommerce.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.carry1st.ecommerce.ui.component.BottomAppNavigation
import com.carry1st.ecommerce.ui.navigation.Navigation

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppNavigation(navHostController = navController)
        }
    ) { innerPadding ->
        Navigation(navHostController = navController)
    }
}