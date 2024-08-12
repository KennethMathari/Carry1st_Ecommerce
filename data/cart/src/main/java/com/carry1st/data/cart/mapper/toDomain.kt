package com.carry1st.data.cart.mapper

import com.carry1st.core.database.entity.CartEntity
import com.carry1st.domain.cart.model.CartDomain

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