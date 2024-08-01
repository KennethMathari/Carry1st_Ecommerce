package com.carry1st.ecommerce.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carry1st.ecommerce.data.repository.ProductRepository
import com.carry1st.ecommerce.domain.utils.Constants.PRODUCTLIST_CLIENT_ERRORMESSAGE
import com.carry1st.ecommerce.domain.utils.Constants.PRODUCTLIST_NETWORK_ERRORMESSAGE
import com.carry1st.ecommerce.domain.utils.Constants.PRODUCTLIST_SERVER_ERRORMESSAGE
import com.carry1st.ecommerce.domain.utils.NetworkResult
import com.carry1st.ecommerce.ui.mapper.toProductPresentation
import com.carry1st.ecommerce.ui.state.ProductListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState: StateFlow<ProductListState> get() = _productListState

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {

            _productListState.value = ProductListState(
                productList = null, isLoading = true, errorMessage = null
            )

            productRepository.getProductList().collect { result ->
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
                        _productListState.value = ProductListState(
                            productList = result.data.map { productDomain ->
                                productDomain.toProductPresentation()
                            }, isLoading = false, errorMessage = null
                        )
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(errorMessage: String) {
        _productListState.value = ProductListState(
            productList = null, isLoading = false, errorMessage = errorMessage
        )
    }
}