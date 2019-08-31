package com.example.drinkshop.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.drinkshop.R;
import com.example.drinkshop.adaptor.FavoriteAdapter;
import com.example.drinkshop.dataBase.modelDB.Favorite;
import com.example.drinkshop.utils.Consts;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recycler = findViewById(R.id.recycler_favorite_activity);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        List<Favorite> favoriteList =  Consts.favoriteRepository.getFavorites().blockingFirst();
        Log.i("00", "onCreate: "+favoriteList.size());
        displayFavItem(favoriteList);

    }

    private void displayFavItem(List<Favorite> favoriteList) {


        Log.i("********", "displayCartItem: "+favoriteList.size());
        if (favoriteList.size()>0) {
            recycler.setAdapter(new FavoriteAdapter( favoriteList,FavoriteActivity.this));
        }

    }
}
