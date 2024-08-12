package com.carry1st.product.ui.mapper

import com.carry1st.cart.ui.model.CartPresentation
import com.carry1st.product.model.ProductDomain
import com.carry1st.product.ui.model.ProductPresentation

fun ProductDomain.toProductPresentation(): ProductPresentation {
    return ProductPresentation(
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

fun ProductPresentation.toCartPresentation(): CartPresentation{
    return CartPresentation(
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