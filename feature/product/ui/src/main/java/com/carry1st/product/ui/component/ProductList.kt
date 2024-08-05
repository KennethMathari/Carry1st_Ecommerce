package com.carry1st.product.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carry1st.product.ui.model.ProductPresentation
import com.carry1st.product.ui.state.ProductListState
import com.carry1st.product.ui.viewmodel.ProductListViewModel

@Composable
fun ProductList(
    modifier: Modifier,
    productListState: ProductListState,
    onProductClicked: (ProductPresentation) -> Unit,
    productListViewModel: ProductListViewModel
) {
    Column {

        OutlinedTextField(
            value = productListState.searchQuery ,
            onValueChange = productListViewModel::searchProductList,
            label = {
                Text(text = "Search")
            },
            placeholder = {
                Text(text = "Search for product..")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            ),
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        LazyColumn {
            items(productListState.productList ?: emptyList()) { product ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ), modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(onClick = {
                            onProductClicked(product)
                        })

                ) {
                    Row {
                        AsyncImage(
                            model = product.imageLocation,
                            contentDescription = "${product.name} image",
                            modifier = modifier
                                .size(100.dp)
                                .padding(start = 8.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 8.dp, top = 8.dp)
                        ) {
                            Text(
                                text = product.name, style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row {
                                Text(
                                    text = product.currencyCode,
                                    modifier = modifier.padding(end = 3.dp),
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = product.price.toString(),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                        }

                    }
                }
            }

        }
    }
}