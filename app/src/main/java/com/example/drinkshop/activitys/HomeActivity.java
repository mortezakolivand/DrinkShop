package com.example.drinkshop.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.drinkshop.R;
import com.example.drinkshop.adaptor.CategoryAdaptor;
import com.example.drinkshop.dataBase.DataSource.CartRepository;
import com.example.drinkshop.dataBase.DataSource.FavoriteRepository;
import com.example.drinkshop.dataBase.local.EDMTRoomDataBase;
import com.example.drinkshop.dataBase.local.CartDataSource;
import com.example.drinkshop.dataBase.local.FavoriteDataSource;
import com.example.drinkshop.model.Category;
import com.example.drinkshop.utils.Consts;
import com.example.drinkshop.utils.TinyDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_CODE = 43 ;
    SliderLayout sliderLayout;
    RecyclerView recyclerView_catg;

    NotificationBadge badge;

    ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sliderLayout = findViewById(R.id.slider);
        addPicSlider();


        recyclerView_catg = findViewById(R.id.recycler_catg);
        recyclerView_catg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView_catg.setHasFixedSize(true);
        List<Category> categories=createListCatg();
        CategoryAdaptor categoryAdaptor = new CategoryAdaptor(HomeActivity.this,categories);
        recyclerView_catg.setAdapter(categoryAdaptor);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getBackground().setAlpha(122);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView name_tv  =(TextView) headerView.findViewById(R.id.name_view);
        TextView phone_tv = (TextView)headerView.findViewById(R.id.phone_view);
        imgview = (ImageView) headerView.findViewById(R.id.imageView);

        TinyDB tinyDB = new TinyDB(HomeActivity.this);

        if (tinyDB.getString(Consts.NAME_USER)==null){
            name_tv.setText("Un named");
        }

        if (tinyDB.getString(Consts.IMG_USER)!=null && tinyDB.getString(Consts.IMG_USER)!=""){
            Log.i("++", "onCreate: "+tinyDB.getString(Consts.IMG_USER));
            imgview.setImageURI(Uri.parse(tinyDB.getString(Consts.IMG_USER)));
        }

        phone_tv.setText(tinyDB.getString(Consts.PHONE_NUMBER_REGISTERATION));

        name_tv.setText("Un named");

        initDB();

        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPic();
            }
        });

    }

    private void initDB() {

        Consts.eDMTRoomDataBase = EDMTRoomDataBase.getInstance(HomeActivity.this);
        Consts.cartRepository= CartRepository.getInstance(CartDataSource.getInstance(Consts.eDMTRoomDataBase.cartDao()));
        Consts.favoriteRepository = FavoriteRepository.getInstance(FavoriteDataSource.getInstance(Consts.eDMTRoomDataBase.favoriteDAO()));

    }

    private List<Category> createListCatg() {

        List<Category> categories = new ArrayList<>();

        categories.add(new Category("cocktail",R.drawable.catg_cocktail));
        categories.add(new Category("coffee",R.drawable.catg_coffee));
        categories.add(new Category("healthy",R.drawable.catg_healthy));
        categories.add(new Category("paradise",R.drawable.catg_paradise));
        categories.add(new Category("tea",R.drawable.catg_tea));

        return  categories;
    }

    private void addPicSlider() {


        int images[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};

        for (int i=0 ;i<images.length ;i++) {

            TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
            textSliderView.description("number : "+String.valueOf(i+1)).
                    image(images[i]).
                    setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);

        }



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_menu, menu);

//        //cart notif
//
        MenuItem menuItem = menu.findItem(R.id.xxx);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //return false;
                startActivity(new Intent(HomeActivity.this,CartActivity.class));
                return true;
            }
        });

        //View view1 = MenuItemCompat.getActionView(menuItem);

        //view1.findViewById(R.id.ic_cart);

//
//        if (menuItem == null){
//            System.out.println("@@@@ + view1");
//        }else{
//            System.out.println("view1");
//        }

        //TextView tv =view1.findViewById(R.id.ttt_tv);
        //ImageView tv2 =view1.findViewById(R.id.img_menuItem);

       // tv.setText("1");

       // tv2.setImageResource(R.drawable.user);

//        //View view = menu.findItem(R.id.xxx).getActionView();
//
//        //TextView textView =view1.findViewById(R.id.ttt_tv);
//
//        //TextView tv= view.findViewById(R.id.ttt_tv);
//
//        //NotificationBadge badge = (NotificationBadge) view.findViewById(R.id.badge);
//
////        if (view.findViewById(R.id.badge)!=null) {
////
////        }
//        //updateCartCount();
////        badge.setVisibility(View.VISIBLE);
////        badge.setText("1");
        return true;
    }

    private void updateCartCount() {
        if (badge==null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                badge.setVisibility(View.VISIBLE);
                badge.setText("1");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       // updateCartCount();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorite) {
            startActivity(new Intent(HomeActivity.this,FavoriteActivity.class));
        }else if (id == R.id.nav_sign_out) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void findPic(){

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            Uri selectedImage = data.getData();
            imgview.setImageURI(selectedImage);
            TinyDB tinyDB = new TinyDB(HomeActivity.this);
            tinyDB.putString(Consts.IMG_USER,selectedImage.toString());

        }else{
           // Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

        }
    }
}
