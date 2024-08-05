package com.carry1st.product.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductPresentation(
    val currencyCode: String,
    val currencySymbol: String,
    val description: String,
    val id: Int,
    val imageLocation: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: String
): Parcelable
