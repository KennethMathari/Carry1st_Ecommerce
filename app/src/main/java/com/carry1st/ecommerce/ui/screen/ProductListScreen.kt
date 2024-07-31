package com.carry1st.ecommerce.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carry1st.ecommerce.ui.component.LoadingScreen
import com.carry1st.ecommerce.ui.viewmodel.ProductListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier, productListViewModel: ProductListViewModel = koinViewModel()
) {

    val productListState by productListViewModel.productListState.collectAsStateWithLifecycle()

    Box {
        if (productListState.isLoading) {
            LoadingScreen()
        } else {
            Column {

                LazyColumn {

                }
            }
        }
    }
}