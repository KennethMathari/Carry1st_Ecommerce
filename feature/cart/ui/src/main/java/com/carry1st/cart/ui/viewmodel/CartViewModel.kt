package com.carry1st.cart.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carry1st.cart.ui.mapper.toCartDomain
import com.carry1st.cart.ui.mapper.toCartPresentation
import com.carry1st.cart.ui.model.CartPresentation
import com.carry1st.cart.ui.state.CartState
import com.carry1st.data.cart.repository.CartRepository
import com.carry1st.domain.cart.utils.DatabaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> get() = _cartState.asStateFlow()

    init {
        getCartItems()
    }

    fun addCartItem(cartItem: CartPresentation) {
        viewModelScope.launch {
            cartRepository.addCartItem(cartItem.toCartDomain())
        }
    }

    private fun getCartItems() {
        viewModelScope.launch {
            cartRepository.getCartItems().collect { result ->

                when (result) {
                    is DatabaseResult.Error -> {
                        _cartState.value = CartState(
                            cartList = null, errorMessage = "Unable to Fetch Cart Items!"
                        )
                    }

                    is DatabaseResult.Success -> {
                        _cartState.value = CartState(
                            cartList = result.data.map { it.toCartPresentation() },
                            errorMessage = null
                        )
                    }
                }

            }
        }
    }

    fun deleteCartItem(cartItem: CartPresentation) {
        viewModelScope.launch {
            cartRepository.deleteCartItem(cartItem.toCartDomain())
        }
    }
}