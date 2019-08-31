package com.example.drinkshop.adaptor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinkshop.R;
import com.example.drinkshop.activitys.DrinkActivity;
import com.example.drinkshop.interfaces.IItemClickListener;
import com.example.drinkshop.model.Category;
import com.example.drinkshop.model.Drink;
import com.example.drinkshop.utils.Consts;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.CategoryViewHolder> {


    Context context;
    List<Category> categories;

    public CategoryAdaptor(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.catg_item_layout,null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.image_catg.setImageResource(categories.get(position).getLink());
        holder.text_catg.setText(categories.get(position).getName());

        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onclick(View v) {

                Consts.CURRENT_USER = categories.get(position);

                //start new activity
                context.startActivity(new Intent(context, DrinkActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    //inner class

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView text_catg;
        ImageView image_catg;

        IItemClickListener iItemClickListener;
        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            text_catg =itemView.findViewById(R.id.text_catg);
            image_catg=itemView.findViewById(R.id.image_catg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iItemClickListener.onclick(v);
        }
    }



}
