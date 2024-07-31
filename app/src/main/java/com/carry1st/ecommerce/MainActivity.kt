package com.carry1st.ecommerce

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.carry1st.ecommerce.data.repository.ProductRepository
import com.carry1st.ecommerce.domain.utils.NetworkResult
import com.carry1st.ecommerce.ui.App
import com.carry1st.ecommerce.ui.theme.Carry1stTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val productRepository: ProductRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            productRepository.getProductList().collect { result ->
                when (result) {
                    is NetworkResult.ClientError -> {
                        Log.e("ClientError:", result.error)
                    }

                    is NetworkResult.NetworkError -> {
                        Log.e("NetworkError:", result.error)
                    }

                    is NetworkResult.ServerError -> {
                        Log.e("ClientError:", result.error)
                    }

                    is NetworkResult.Success -> {
                        Log.e("Success:", result.data.toString())
                    }
                }
            }
        }
        setContent {
            Carry1stTheme {
                App()
            }
        }
    }
}

