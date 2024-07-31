package com.carry1st.ecommerce.ui.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carry1st.ecommerce.ui.destination.ProductList
import com.carry1st.ecommerce.ui.screens.ProductListScreen

@Composable
fun Navigation(
    navHostController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navHostController, startDestination = ProductList
    ) {

        composable<ProductList> {
            ProductListScreen()
        }

    }

}