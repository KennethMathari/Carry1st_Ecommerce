package com.carry1st.ecommerce.data.mapper

import com.carry1st.ecommerce.data.local.entity.CartEntity
import com.carry1st.ecommerce.data.local.entity.ProductEntity
import com.carry1st.ecommerce.domain.model.CartDomain
import com.carry1st.ecommerce.domain.model.ProductDomain

fun ProductDomain.toProductEntity(): ProductEntity{
    return ProductEntity(
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

fun CartDomain.toCartEntity(): CartEntity{
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