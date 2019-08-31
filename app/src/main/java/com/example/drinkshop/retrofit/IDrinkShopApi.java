package com.example.drinkshop.retrofit;

import com.example.drinkshop.model.CheckUserResponse;
import com.example.drinkshop.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IDrinkShopApi {

    @FormUrlEncoded()
    @POST()
    Call<CheckUserResponse> checkUserExists(@Field("Phone") String Phone);


    @FormUrlEncoded()
    @POST()
    Call<User> registerNewUser(@Field("Phone") String phone,
                               @Field("name") String name,
                               @Field("address") String address,
                               @Field("birthday") String birthday
                               );

    @FormUrlEncoded()
    @POST()
    Call<User> getUserInformation(@Field("Phone") String phone);


}
