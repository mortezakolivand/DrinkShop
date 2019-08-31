package com.example.drinkshop.dataBase.DataSource;

import com.example.drinkshop.dataBase.modelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements IFavoriteDataSource {

    private IFavoriteDataSource favoriteDataSource;

    public FavoriteRepository(IFavoriteDataSource favoriteDataSource) {
        this.favoriteDataSource = favoriteDataSource;
    }

    private static FavoriteRepository instance;

    public static FavoriteRepository getInstance(IFavoriteDataSource iFavoriteDataSource){
        if (instance == null){
            instance = new FavoriteRepository(iFavoriteDataSource);
        }
        return instance;
    }


    @Override
    public Flowable<List<Favorite>> getFavorites() {
        return favoriteDataSource.getFavorites();
    }

    @Override
    public int isFavorite(int item) {
        return favoriteDataSource.isFavorite(item);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteDataSource.delete(favorite);
    }

    @Override
    public void insertFav(Favorite... favorites) {
        favoriteDataSource.insertFav(favorites);
    }
}
