package com.carry1st.ecommerce.ui.navigation

import com.carry1st.ecommerce.R
import com.carry1st.ecommerce.ui.navigation.destination.Cart
import com.carry1st.ecommerce.ui.navigation.destination.ProductListDetail
import com.carry1st.ecommerce.ui.navigation.destination.Settings
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavigationItem<T>(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasUpdate: Boolean,
    val badgeCount: Int? = null,
    val route: T
) {

    @Serializable
    data object ProductListItem : BottomNavigationItem<ProductListDetail>(
        title = "Products",
        selectedIcon = R.drawable.filled_list,
        unselectedIcon = R.drawable.outlined_list,
        hasUpdate = false,
        badgeCount = null,
        route = ProductListDetail
    )

    @Serializable
    data object CartItem : BottomNavigationItem<Cart>(
        title = "Cart",
        selectedIcon = R.drawable.filled_cart,
        unselectedIcon = R.drawable.outlined_cart,
        hasUpdate = false,
        badgeCount = 2,
        route = Cart
    )

    @Serializable
    data object SettingsItem : BottomNavigationItem<Settings>(
        title = "Settings",
        selectedIcon = R.drawable.filled_settings,
        unselectedIcon = R.drawable.outlined_settings,
        hasUpdate = false,
        badgeCount = null,
        route = Settings
    )

}