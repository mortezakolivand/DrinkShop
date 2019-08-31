package com.example.drinkshop.dataBase.local;

import com.example.drinkshop.dataBase.DataSource.IFavoriteDataSource;
import com.example.drinkshop.dataBase.modelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDataSource implements IFavoriteDataSource {

    private FavoriteDAO favoriteDAO;
    public static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDAO favoriteDAO) {
        this.favoriteDAO = favoriteDAO;
    }

    public static FavoriteDataSource getInstance(FavoriteDAO favoriteDAO) {

        if (instance == null) {
            instance = new FavoriteDataSource(favoriteDAO);
        }
        return instance;
    }

    @Override
    public Flowable<List<Favorite>> getFavorites() {
        return favoriteDAO.getFavorites();
    }

    @Override
    public int isFavorite(int item) {
        return favoriteDAO.isFavorite(item);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteDAO.delete(favorite);
    }

    @Override
    public void insertFav(Favorite... favorites) {
        favoriteDAO.insertFav(favorites);
    }
}
