package com.carry1st.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.carry1st.ecommerce.ui.App
import com.carry1st.ecommerce.ui.theme.Carry1stTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            Carry1stTheme {
                App()
            }
        }
    }
}

