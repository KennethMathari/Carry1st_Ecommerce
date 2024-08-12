package com.carry1st.product.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carry1st.product.ui.model.ProductPresentation

@Composable
fun BuyNow(
    product: ProductPresentation, modifier: Modifier
) {

    Text(text = "Buy Now")
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = product.name)
        Text(text = "Total Price:${product.currencyCode} ${product.price}")
        Text(text = "Choose Payment Method")



    }
}