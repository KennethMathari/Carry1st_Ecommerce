package com.carry1st.data.cart.mapper

import com.carry1st.core.database.entity.CartEntity
import com.carry1st.domain.cart.model.CartDomain

fun CartDomain.toCartEntity(): CartEntity {
    return CartEntity(
        id = this.id,
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        description = this.description,
        imageLocation = this.imageLocation,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        status = this.status
    )
}