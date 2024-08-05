package com.carry1st.cart.ui.mapper

import com.carry1st.cart.ui.model.CartPresentation
import com.carry1st.domain.model.CartDomain

fun CartPresentation.toCartDomain(): CartDomain {
    return CartDomain(
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        description = this.description,
        id = this.id,
        imageLocation = this.imageLocation,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        status = this.status
    )
}