package com.example.drinkshop.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.drinkshop.R;
import com.example.drinkshop.adaptor.CartAdaptor;
import com.example.drinkshop.dataBase.modelDB.Cart;
import com.example.drinkshop.utils.Consts;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity {

    RecyclerView recycler;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recycler = findViewById(R.id.recycler_cart_activity);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        List<Cart> carts =  Consts.cartRepository.getCartItems().blockingFirst();
        Log.i("00", "onCreate: "+carts.size());
        displayCartItem(carts);


       // loaditemCarts(carts);

    }

    private void loaditemCarts() {
//
//        compositeDisposable.add(
//
//                Consts.cartRepository.getCartItems().observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Cart>>() {
//                    @Override
//                    public void accept(List<Cart> carts) throws Exception {
//                        displayCartItem(carts);
//                    }
//
//                })
//        );

//
//        compositeDisposable.add(Consts.cartRepository.getCartItems()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<List<Cart>>() {
//                    @Override
//                    public void accept(List<Cart> carts) throws Exception {
//                        displayCartItem(carts);
//                    }
//                }));

    }

    private void displayCartItem(List<Cart> carts) {
        if (carts.size()>0) {
            recycler.setAdapter(new CartAdaptor(this, carts));
        }
    }

    @Override
    protected void onDestroy() {
       // compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
       // compositeDisposable.clear();
        super.onStop();
    }
}
