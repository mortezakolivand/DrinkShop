package com.example.drinkshop.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkshop.R;
import com.example.drinkshop.dataBase.modelDB.Favorite;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    List<Favorite> favorites;
    Context context;

    public FavoriteAdapter(List<Favorite> favorites, Context context) {
        this.favorites = favorites;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fav_item_layout,null);
        return new FavoriteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {


        holder.title.setText(favorites.get(position).name);
        holder.price.setText(favorites.get(position).price);
        holder.img.setImageResource(Integer.parseInt(favorites.get(position).link));
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title,price;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_fav_item);
            price = itemView.findViewById(R.id.price_fav_item);
            img=itemView.findViewById(R.id.img_fav_item);

        }
    }

}
