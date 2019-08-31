package com.example.drinkshop.dataBase.modelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    public String name;

    public String amount;

    public String price;

    public String ice;

    public String suger;

    public int link;
}
