package com.example.drinkshop.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.drinkshop.R;
import com.example.drinkshop.adaptor.DrinkAdaptor;
import com.example.drinkshop.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {


    RecyclerView recyclerView_catg_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);


        recyclerView_catg_item = findViewById(R.id.recyclerView_catg_item);
        recyclerView_catg_item.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView_catg_item.setHasFixedSize(true);
        List<Drink> categories=createListCatg();
        DrinkAdaptor drinkAdaptor = new DrinkAdaptor(DrinkActivity.this,categories);
        recyclerView_catg_item.setAdapter(drinkAdaptor);

    }

    private List<Drink> createListCatg() {

        List<Drink> drinks = new ArrayList<>();

        drinks.add(new Drink("1","espresso", R.drawable.catg_item_coffee1,"11000" ));
        drinks.add(new Drink("2","latte", R.drawable.catg_item_coffee2,"18000" ));
        drinks.add(new Drink("3","Americano", R.drawable.catg_item_coffee_double_espresso,"14000" ));
        drinks.add(new Drink("4","mocha", R.drawable.catg_item_coffee_mocha,"14000" ));
        drinks.add(new Drink("5","cappuccino", R.drawable.catg_item_coffee_mocha,"14000" ));
        drinks.add(new Drink("6","double espresso", R.drawable.catg_item_coffee_double_espresso,"14000" ));
        drinks.add(new Drink("7","almond latte", R.drawable.catg_item_coffee_almond_latte,"14000" ));

        return drinks;

    }
}
