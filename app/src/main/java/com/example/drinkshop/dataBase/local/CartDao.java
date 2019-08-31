package com.example.drinkshop.dataBase.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drinkshop.dataBase.modelDB.Cart;
import com.example.drinkshop.model.Drink;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CartDao {

    @Query("SELECT * FROM Cart")
    Flowable<List<Cart>> getCartItems();

    @Query("SELECT * FROM Cart where id=:cartItemid")
    Flowable<List<Cart>> getCartById(int cartItemid);


    @Query("select COUNT(*) from Cart")
    int getCount();

    @Query("DELETE FROM Cart")
    void emptyCart();

    @Insert
    void insertToCart(Cart...carts);

    @Update
    void updateCart(Cart...carts);

    @Delete
    void deleteCartItem(Cart cart);

}
