package com.example.drinkshop.utils;

import com.example.drinkshop.retrofit.IDrinkShopApi;
import com.example.drinkshop.retrofit.RetrofitClient;

public class Common {

    private static final String BASE_URL="localhost/drinkshop";


    public static IDrinkShopApi getApi(){
      return   RetrofitClient.getClient(BASE_URL).create(IDrinkShopApi.class);
    }

}
