package com.example.drinkshop.dataBase.DataSource;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drinkshop.dataBase.modelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface ICartDataSource {

    Flowable<List<Cart>> getCartItems();

    Flowable<List<Cart>>  getCartById(int cartItemid);

    int getCount();

    void emptyCart();

    void insertToCart(Cart...carts);

    void updateCart(Cart...carts);

    void deleteCartItem(Cart cart);

}
