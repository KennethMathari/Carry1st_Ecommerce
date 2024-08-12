package com.carry1st.product.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carry1st.data.product.local.SearchManager
import com.carry1st.data.product.repository.ProductRepository
import com.carry1st.product.ui.mapper.toProductPresentation
import com.carry1st.product.ui.state.ProductListState
import com.carry1st.product.ui.utils.Constants.PRODUCTLIST_CLIENT_ERRORMESSAGE
import com.carry1st.product.ui.utils.Constants.PRODUCTLIST_NETWORK_ERRORMESSAGE
import com.carry1st.product.ui.utils.Constants.PRODUCTLIST_SERVER_ERRORMESSAGE
import com.carry1st.product.utils.ApiResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductListViewModel(
    private val productRepository: ProductRepository, private val searchManager: SearchManager
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState: StateFlow<ProductListState> get() = _productListState.asStateFlow()

    private var searchJob: Job? = null

    init {
        getProductListFromServer()
        fetchProductListFromLocalStorage()
    }

    private fun fetchProductListFromLocalStorage() {
        viewModelScope.launch {
            val productList =
                searchManager.fetchProductListFromLocalStorage().map { it.toProductPresentation() }
            _productListState.value = ProductListState(
                productList = productList, isLoading = false, errorMessage = null
            )

        }
    }

    fun getProductListFromServer() {
        viewModelScope.launch {
            runBlocking {
                _productListState.value = ProductListState(
                    productList = null, isLoading = true, errorMessage = null
                )

                productRepository.getProductListFromServer().collect { result ->
                    when (result) {
                        is ApiResult.ClientError -> {
                            updateErrorMessage(PRODUCTLIST_CLIENT_ERRORMESSAGE)
                        }

                        is ApiResult.NetworkError -> {
                            updateErrorMessage(PRODUCTLIST_NETWORK_ERRORMESSAGE)
                        }

                        is ApiResult.ServerError -> {
                            updateErrorMessage(PRODUCTLIST_SERVER_ERRORMESSAGE)
                        }

                        is ApiResult.Success -> {
                            searchManager.addProductList(result.data)
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

    fun searchProductList(query: String) {
        _productListState.value = ProductListState(
            searchQuery = query
        )
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            val productList = searchManager.searchProductList(query)

            _productListState.value = ProductListState(
                productList = productList.map { it.toProductPresentation() },
                isLoading = false,
                errorMessage = null,
                searchQuery = query
            )

        }
    }

    override fun onCleared() {
        searchManager.closeSession()
        super.onCleared()
    }
}