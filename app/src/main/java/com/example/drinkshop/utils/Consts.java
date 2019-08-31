package com.example.drinkshop.utils;

import com.example.drinkshop.dataBase.DataSource.CartRepository;
import com.example.drinkshop.dataBase.DataSource.FavoriteRepository;
import com.example.drinkshop.dataBase.local.EDMTRoomDataBase;
import com.example.drinkshop.model.Category;
import com.example.drinkshop.model.Drink;

public class Consts {

    public static String PHONE_NUMBER_REGISTERATION = "PHONE_NUMBER_REGISTERATION";
    public static String NAME_USER = "NAME_USER";
    public static String IMG_USER = "IMAGE_OF_PROFILE";
    public static Category CURRENT_USER = null;
    public static Drink CURRENT_Drink = null;

    public static int ICE_OF_DRINK = -1;
    public static int SUGER_OF_DRINK = -1;
    public static int SIZE_OF_DRINK = -1;

//database
    public static EDMTRoomDataBase eDMTRoomDataBase;
    public static CartRepository cartRepository;
    public static FavoriteRepository favoriteRepository;

}
