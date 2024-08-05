package com.carry1st.product.data.mapper

import com.carry1st.product.data.local.document.ProductDocument
import com.carry1st.product.data.network.model.ProductDTO
import com.carry1st.product.domain.model.ProductDomain

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