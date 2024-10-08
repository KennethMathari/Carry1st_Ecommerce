package com.carry1st.data.product.mapper

import com.carry1st.data.product.local.document.ProductDocument
import com.carry1st.product.model.ProductDomain

fun ProductDomain.toProductDocument(): ProductDocument {
    return ProductDocument(
        namespace = "my_products",
        id = this.id.toString(),
        score = 1,
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