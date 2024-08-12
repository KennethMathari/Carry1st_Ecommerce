package com.carry1st.data.product.mapper

import com.carry1st.data.product.local.document.ProductDocument
import com.carry1st.data.product.network.model.ProductDTO
import com.carry1st.product.model.ProductDomain

fun ProductDTO.toProductDomain(): ProductDomain {
    return ProductDomain(
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

fun ProductDocument.toProductDomain(): ProductDomain {
    return ProductDomain(
        currencyCode = this.currencyCode,
        currencySymbol = this.currencySymbol,
        description = this.description,
        id = this.id.toInt(),
        imageLocation = this.imageLocation,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        status = this.status
    )
}