package com.example.drinkshop.dataBase.modelDB;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorite {

    @NonNull
    @PrimaryKey
    public String id;
    public String name;
    public String link;
    public String price;
    public String menuId;


}
