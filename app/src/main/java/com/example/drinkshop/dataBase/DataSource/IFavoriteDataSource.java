package com.example.drinkshop.dataBase.DataSource;


import com.example.drinkshop.dataBase.modelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {


    Flowable<List<Favorite>> getFavorites();

    int isFavorite(int item);

    void delete(Favorite favorite);

    void insertFav(Favorite...favorites);
}
