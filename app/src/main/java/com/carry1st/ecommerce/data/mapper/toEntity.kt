package com.carry1st.ecommerce.data.mapper

import com.carry1st.ecommerce.data.local.entity.CartEntity
import com.carry1st.ecommerce.domain.model.CartDomain


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