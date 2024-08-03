package com.carry1st.ecommerce.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carry1st.ecommerce.data.repository.cart.CartRepository
import com.carry1st.ecommerce.ui.mapper.toCartDomain
import com.carry1st.ecommerce.ui.mapper.toCartPresentation
import com.carry1st.ecommerce.ui.model.CartPresentation
import com.carry1st.ecommerce.ui.state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> get() = _cartState

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
            cartRepository.getCartItems().catch {
                _cartState.value = CartState(
                    cartList = emptyList(), errorMessage = "Unable to Fetch Cart Items!"
                )
            }.collect { cartDomainList ->
                Log.e("CartList:", cartDomainList.toString())
                _cartState.value = CartState(
                    cartList = cartDomainList.map { it.toCartPresentation() }, errorMessage = null
                )

            }
        }
    }

    fun deleteCartItem(cartItem: CartPresentation) {
        viewModelScope.launch {
            cartRepository.deleteCartItem(cartItem.toCartDomain())
        }
    }
}