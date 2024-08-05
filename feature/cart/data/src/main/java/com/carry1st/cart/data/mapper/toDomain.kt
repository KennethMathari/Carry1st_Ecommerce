package com.carry1st.cart.data.mapper

import com.carry1st.cart.data.local.entity.CartEntity
import com.carry1st.domain.model.CartDomain

fun CartEntity.toCartDomain(): CartDomain {
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