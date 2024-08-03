package com.carry1st.ecommerce.ui.mapper

import com.carry1st.ecommerce.domain.model.CartDomain
import com.carry1st.ecommerce.ui.model.CartPresentation

fun CartPresentation.toCartDomain(): CartDomain{
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