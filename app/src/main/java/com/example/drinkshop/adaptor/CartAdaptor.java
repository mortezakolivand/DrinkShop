package com.example.drinkshop.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.drinkshop.R;
import com.example.drinkshop.dataBase.modelDB.Cart;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.CartViewHolder> {

    Context context;
    List<Cart> carts;

    public CartAdaptor(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,null);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Cart cart = carts.get(position);

        holder.title.setText(cart.name +" X" + cart.amount );
        holder.price.setText(cart.price);
        holder.iceSuger.setText("Ice: "+cart.ice+"Sugar: "+cart.suger);
        holder.img.setImageResource(cart.link);

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView iceSuger,price,title;
        ElegantNumberButton number;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_cart_item);
            img = itemView.findViewById(R.id.img_cart_item);
            price = itemView.findViewById(R.id.price_cart_item);
            iceSuger = itemView.findViewById(R.id.ice_suger_cart_item);
            number = itemView.findViewById(R.id.number_cart_item);


        }
    }

}
