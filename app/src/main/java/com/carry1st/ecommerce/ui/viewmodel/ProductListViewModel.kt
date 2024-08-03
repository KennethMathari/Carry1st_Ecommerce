package com.carry1st.ecommerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carry1st.ecommerce.data.repository.product.ProductRepository
import com.carry1st.ecommerce.domain.utils.Constants.PRODUCTLIST_CLIENT_ERRORMESSAGE
import com.carry1st.ecommerce.domain.utils.Constants.PRODUCTLIST_NETWORK_ERRORMESSAGE
import com.carry1st.ecommerce.domain.utils.Constants.PRODUCTLIST_SERVER_ERRORMESSAGE
import com.carry1st.ecommerce.domain.utils.NetworkResult
import com.carry1st.ecommerce.ui.mapper.toProductPresentation
import com.carry1st.ecommerce.ui.state.ProductListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductListViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState: StateFlow<ProductListState> get() = _productListState

    init {
        getProductListFromServer()
        getProductListFromLocalDB()
    }

    private fun getProductListFromServer() {
        viewModelScope.launch {
            runBlocking {
                _productListState.value = ProductListState(
                    productList = null, isLoading = true, errorMessage = null
                )

                productRepository.getProductListFromServer().collect { result ->
                    when (result) {
                        is NetworkResult.ClientError -> {
                            updateErrorMessage(PRODUCTLIST_CLIENT_ERRORMESSAGE)
                        }

                        is NetworkResult.NetworkError -> {
                            updateErrorMessage(PRODUCTLIST_NETWORK_ERRORMESSAGE)
                        }

                        is NetworkResult.ServerError -> {
                            updateErrorMessage(PRODUCTLIST_SERVER_ERRORMESSAGE)
                        }

                        is NetworkResult.Success -> {
                            productRepository.saveProductsToLocalDB(result.data)
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(errorMessage: String) {
        _productListState.update {
            it.copy(
                productList = null, isLoading = false, errorMessage = errorMessage
            )
        }
    }

    private fun getProductListFromLocalDB() {
        viewModelScope.launch {
            productRepository.getProductListFromLocalDB().catch {
                updateErrorMessage(PRODUCTLIST_CLIENT_ERRORMESSAGE)
            }.collect { productList ->
                _productListState.update {
                    it.copy(
                        productList = productList.map { productDomain -> productDomain.toProductPresentation() },
                        isLoading = false,
                        errorMessage = null
                    )
                }

            }
        }
    }
}