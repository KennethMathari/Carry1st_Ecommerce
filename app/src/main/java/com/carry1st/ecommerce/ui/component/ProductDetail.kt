package com.carry1st.ecommerce.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carry1st.ecommerce.ui.mapper.toCartPresentation
import com.carry1st.ecommerce.ui.model.ProductPresentation
import com.carry1st.ecommerce.ui.viewmodel.CartViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetail(
    productPresentation: ProductPresentation,
    modifier: Modifier,
    cartViewModel: CartViewModel = koinViewModel(),
    navigateToCartScreen: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = productPresentation.imageLocation,
            contentDescription = "${productPresentation.name} image",
            modifier = modifier
                .size(100.dp)
                .padding(start = 8.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = productPresentation.name)
        Text(text = productPresentation.price.toString())
        Text(text = productPresentation.description)

        Button(onClick = {
            cartViewModel.addCartItem(productPresentation.toCartPresentation())
            navigateToCartScreen()
        }) {
            Text(text = "Add to Cart")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Buy Now")
        }
    }

}