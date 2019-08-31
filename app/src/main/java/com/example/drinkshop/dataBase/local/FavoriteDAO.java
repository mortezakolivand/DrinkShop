package com.example.drinkshop.dataBase.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.drinkshop.dataBase.modelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteDAO {

    @Query("select * from Favorite")
    Flowable<List<Favorite>> getFavorites();

    @Query("select exists(select 1 from favorite where id=:item) ")
    int isFavorite(int item);

    @Delete
    void delete(Favorite favorite);

    @Insert
    void insertFav(Favorite...favorites);

}
