package com.example.drinkshop.dataBase.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.drinkshop.dataBase.modelDB.Cart;
import com.example.drinkshop.dataBase.modelDB.Favorite;

@Database(entities = {Cart.class, Favorite.class} , version = 1)
public abstract class EDMTRoomDataBase extends RoomDatabase {

    public abstract CartDao cartDao();
    public abstract FavoriteDAO favoriteDAO ();

    private static EDMTRoomDataBase instance;

    public static EDMTRoomDataBase getInstance(Context context){

        if (instance==null){
            instance = Room.databaseBuilder(context, EDMTRoomDataBase.class , "DB_DrinkShop").
                        allowMainThreadQueries().
                            build();
        }

        return instance;

    }

}
