package com.example.drinkshop.adaptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.drinkshop.R;
import com.example.drinkshop.activitys.DrinkActivity;
import com.example.drinkshop.activitys.HomeActivity;
import com.example.drinkshop.dataBase.modelDB.Cart;
import com.example.drinkshop.dataBase.modelDB.Favorite;
import com.example.drinkshop.model.Drink;
import com.example.drinkshop.utils.Consts;
import com.google.gson.Gson;

import java.util.List;

public class DrinkAdaptor extends RecyclerView.Adapter<DrinkAdaptor.DrinkViewHolder> {

    Context context;
    List<Drink> drinks ;

    public DrinkAdaptor(Context context, List<Drink> drinks) {
        this.context = context;
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.drink_item_layout,null);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {

        holder.name_tv.setText(drinks.get(position).getName());
        holder.price_tv.setText(drinks.get(position).getPrice());
        holder.imageView_drink.setImageResource(drinks.get(position).getLink());

        holder.addToCart_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAddToCard(position);

            }
        });

        if (Consts.favoriteRepository.isFavorite(Integer.parseInt(drinks.get(position).getId()))==1){
            holder.favorite_img.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else{
            holder.favorite_img.setImageResource(R.drawable.ic_favorite_white_24dp);
        }

        holder.favorite_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Consts.favoriteRepository.isFavorite(Integer.parseInt(drinks.get(position).getId()))==1){
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                    addOrRemoveFavorite(drinks.get(position) , false);
                    holder.favorite_img.setImageResource(R.drawable.ic_favorite_white_24dp);
                }else{
                    Toast.makeText(context,String.valueOf(Consts.favoriteRepository.isFavorite(Integer.parseInt(drinks.get(position).getId()))), Toast.LENGTH_SHORT).show();
                    addOrRemoveFavorite(drinks.get(position) , true);
                    holder.favorite_img.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

            }
        });


    }

    private void addOrRemoveFavorite(Drink drink ,Boolean isAdd) {

        Favorite favorite = new Favorite();
        favorite.id= drink.getId();
        favorite.link =String.valueOf(drink.getLink());
        favorite.menuId = drink.getMenuId();
        favorite.price= drink.getPrice();
        favorite.name=drink.getName();

        if (isAdd){
            Consts.favoriteRepository.insertFav(favorite);
        }else {
            Consts.favoriteRepository.delete(favorite);
        }

    }

    private void showAddToCard(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.add_to_cart_layout,null);

        //view
        ImageView image_product = view.findViewById(R.id.addtocart_img);
        ElegantNumberButton elegantNumberCount = view.findViewById(R.id.elegantNumberButton);
        TextView text_product = view.findViewById(R.id.title_tv);

        RadioButton sizeM = view.findViewById(R.id.size_m);
        sizeM.setOnClickListener(v -> Consts.SIZE_OF_DRINK=1);
        RadioButton sizel = view.findViewById(R.id.size_l);
        sizeM.setOnClickListener(v -> Consts.SIZE_OF_DRINK=2);

        RadioButton ice100 = view.findViewById(R.id.ice_radio_100);
        ice100.setOnClickListener(v -> Consts.ICE_OF_DRINK=100);
        RadioButton ice70 = view.findViewById(R.id.ice_radio_70);
        ice70.setOnClickListener(v -> Consts.ICE_OF_DRINK=70);
        RadioButton ice50 = view.findViewById(R.id.ice_radio_50);
        ice50.setOnClickListener(v -> Consts.ICE_OF_DRINK=50);
        RadioButton ice30 = view.findViewById(R.id.ice_radio_30);
        ice30.setOnClickListener(v -> Consts.ICE_OF_DRINK=30);

        RadioButton suger100 = view.findViewById(R.id.suger_radio_100);
        suger100.setOnClickListener(v -> Consts.SUGER_OF_DRINK=100);
        RadioButton suger70 = view.findViewById(R.id.suger_radio_70);
        suger70.setOnClickListener(v -> Consts.SUGER_OF_DRINK=70);
        RadioButton suger50 = view.findViewById(R.id.suger_radio_50);
        suger50.setOnClickListener(v -> Consts.SUGER_OF_DRINK=50);
        RadioButton suger30 = view.findViewById(R.id.suger_radio_30);
        suger30.setOnClickListener(v -> Consts.SUGER_OF_DRINK=30);

//        RecyclerView recyclerView = view.findViewById(R.id.topping_Recycle);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setHasFixedSize(true);


        Drink drink=drinks.get(position);

        image_product.setImageResource(drink.getLink());
        text_product.setText(drink.getName());

        builder.setView(view);
        builder.setNegativeButton("Add To Card", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "bang bang", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

                showConfimlayout(drink ,elegantNumberCount.getNumber(), Consts.ICE_OF_DRINK , Consts.SUGER_OF_DRINK , Consts.SIZE_OF_DRINK );
            }

        });

        builder.show();

    }

    private void showConfimlayout(Drink drink , String number ,int ice ,int suger ,int size) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.confirm_add_to_cart_layout,null);

        ImageView imageView =  view.findViewById(R.id.confirmAddToCart_img);
        imageView.setImageResource(drink.getLink());

        TextView titleConfirm_tv = view.findViewById(R.id.confirm_title_tv);
        titleConfirm_tv.setText(drink.getName() + "  X" +number);

        TextView suger_tv = view.findViewById(R.id.confirm_suger_percent_tv);
        suger_tv.setText(String.valueOf(suger));

        TextView ice_tv = view.findViewById(R.id.confirm_ice_percent_tv);
        ice_tv.setText(String.valueOf(ice));

        TextView price_tv = view.findViewById(R.id.confirm_price_tv);
        int count_price=Integer.parseInt(drink.getPrice()) * Integer.parseInt(number);
        price_tv.setText( String.valueOf(count_price));


        builder.setView(view);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Cart cart = new Cart();
                cart.name = drink.getName();
                cart.amount=number;
                cart.ice=String.valueOf(ice);
                cart.suger=String.valueOf(suger);
                cart.price = String.valueOf(count_price);
                cart.link=drink.getLink();

                Log.i("****",new Gson().toJson(cart));

                Consts.cartRepository.insertToCart(cart);

                dialog.dismiss();

                context.startActivity(new Intent(context, HomeActivity.class));
            }
        });

        builder.show();

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }


    //inner class

    class DrinkViewHolder extends RecyclerView.ViewHolder  {

        TextView price_tv;
        TextView name_tv;
        ImageView imageView_drink;
        ImageView favorite_img;
        TextView addToCart_tv;


        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_drink = itemView.findViewById(R.id.image_drink);
            name_tv = itemView.findViewById(R.id.text_drink);
            price_tv = itemView.findViewById(R.id.text_price);
            addToCart_tv = itemView.findViewById(R.id.addtocart);
            favorite_img = itemView.findViewById(R.id.favorite_drink_item);
        }


    }

}
