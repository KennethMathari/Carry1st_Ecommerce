package com.carry1st.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.carry1st.core.database.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCartList(): Flow<List<CartEntity>>

    @Upsert
    fun addCartItem(cartItem: CartEntity)

    @Delete
    fun deleteCartItem(cartItem: CartEntity)
}