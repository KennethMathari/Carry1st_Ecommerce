package com.carry1st.ecommerce.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carry1st.ecommerce.ui.component.LoadingScreen
import com.carry1st.ecommerce.ui.component.ProductDetail
import com.carry1st.ecommerce.ui.component.ProductList
import com.carry1st.ecommerce.ui.model.ProductPresentation
import com.carry1st.ecommerce.ui.viewmodel.ProductListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ProductListDetailScreen(
    modifier: Modifier = Modifier,
    productListViewModel: ProductListViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    navigateToCartScreen: () -> Unit
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
                            onProductClicked = { productPresentation ->
                                navigator.navigateTo(
                                    ListDetailPaneScaffoldRole.Detail, productPresentation
                                )
                            })
                    }
                },
                detailPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let { productPresentation ->
                            ProductDetail(
                                modifier = modifier,
                                productPresentation = productPresentation,
                                navigateToCartScreen = navigateToCartScreen
                            )
                        }
                    }
                })

            LaunchedEffect(productListState.errorMessage) {
                scope.launch {
                    productListState.errorMessage?.let { errorMessage ->
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }

            }
        }
    }
}



