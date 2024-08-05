package com.carry1st.ecommerce.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carry1st.cart.ui.screen.CartScreen
import com.carry1st.ecommerce.ui.navigation.destination.Cart
import com.carry1st.ecommerce.ui.navigation.destination.ProductListDetail
import com.carry1st.ecommerce.ui.navigation.destination.Settings
import com.carry1st.ecommerce.ui.screen.SettingsScreen
import com.carry1st.product.ui.screen.ProductListDetailScreen

@Composable
fun Navigation(
    navHostController: NavHostController, snackbarHostState: SnackbarHostState
) {

    val scope = rememberCoroutineScope()

    NavHost(
        navController = navHostController, startDestination = ProductListDetail
    ) {

        composable<ProductListDetail> {
            ProductListDetailScreen(snackbarHostState = snackbarHostState,
                scope = scope,
                navigateToCartScreen = {
                    navHostController.navigate(Cart)
                })
        }

        composable<Cart> {
            CartScreen()
        }

        composable<Settings> {
            SettingsScreen()
        }

    }

}