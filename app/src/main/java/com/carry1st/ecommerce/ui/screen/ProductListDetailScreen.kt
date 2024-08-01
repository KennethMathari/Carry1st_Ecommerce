package com.carry1st.ecommerce.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carry1st.ecommerce.ui.component.LoadingScreen
import com.carry1st.ecommerce.ui.model.ProductPresentation
import com.carry1st.ecommerce.ui.state.ProductListState
import com.carry1st.ecommerce.ui.viewmodel.ProductListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ProductListDetailScreen(
    modifier: Modifier = Modifier, productListViewModel: ProductListViewModel = koinViewModel()
) {

    val productListState by productListViewModel.productListState.collectAsStateWithLifecycle()
    val navigator = rememberListDetailPaneScaffoldNavigator<ProductPresentation>()

    Box {
        if (productListState.isLoading) {
            LoadingScreen()
        } else {

            BackHandler(navigator.canNavigateBack()) {
                navigator.navigateBack()
            }

            ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    AnimatedPane {
                        ProductList(modifier = modifier,
                            productListState = productListState,
                            onProductClicked = {
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
                            })
                    }
                },
                detailPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let {
                            ProductDetail(productPresentation = it)
                        }
                    }
                })
        }
    }
}

@Composable
fun ProductList(
    modifier: Modifier,
    productListState: ProductListState,
    onProductClicked: (ProductPresentation) -> Unit
) {
    Column {
        LazyColumn {
            items(productListState.productList ?: emptyList()) { product ->
                Card(colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ), modifier = modifier
                    .clickable(onClick = {
                        onProductClicked(product)
                    })
                    .fillMaxWidth()
                ) {
                    Text(text = product.name)
                }
            }

        }
    }
}

@Composable
fun ProductDetail(
    productPresentation: ProductPresentation
) {
    Column {
        Text(text = productPresentation.name)
        Text(text = productPresentation.description)
        Text(text = productPresentation.status)
        Text(text = productPresentation.currencyCode)
    }

}