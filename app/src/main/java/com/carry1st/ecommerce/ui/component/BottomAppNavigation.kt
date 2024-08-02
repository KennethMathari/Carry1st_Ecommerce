package com.carry1st.ecommerce.ui.component

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.carry1st.ecommerce.ui.navigation.BottomNavigationItem

@Composable
fun BottomAppNavigation(
    navHostController: NavHostController
) {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val bottomNavItems = remember {
        listOf(
            BottomNavigationItem.ProductListItem,
            BottomNavigationItem.CartItem,
            BottomNavigationItem.SettingsItem
        )
    }

    NavigationBar {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                label = {
                    Text(text = item.title)
                },
                selected = selectedItemIndex == index, 
                onClick = {
                selectedItemIndex = index
                navHostController.navigate(item.route)
            }, 
                icon = {
                BadgedBox(badge = {
                    if (item.badgeCount != null) {
                        Badge {
                            Text(text = item.badgeCount.toString())
                        }
                    } else if (item.hasUpdate){
                        Badge()
                    }
                }) {
                    Icon(
                        painter = if (index == selectedItemIndex) {
                            painterResource(id = item.selectedIcon)
                        } else painterResource(id = item.unselectedIcon),
                        contentDescription = item.title
                    )
                }
            })

        }
    }
}