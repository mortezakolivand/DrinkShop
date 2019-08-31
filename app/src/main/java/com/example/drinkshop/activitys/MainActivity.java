package com.example.drinkshop.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.drinkshop.R;
import com.example.drinkshop.utils.Consts;
import com.example.drinkshop.utils.TinyDB;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    Button button_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TinyDB tinyDB = new TinyDB(this);

        if (tinyDB.getString(Consts.PHONE_NUMBER_REGISTERATION)!=null){
            Log.i("****", "onCreate: "+tinyDB.getString(Consts.PHONE_NUMBER_REGISTERATION).toString());
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }

        button_continue = findViewById(R.id.continue_btn);
        button_continue.setOnClickListener(v->btnContinueOnclick() );
    }

    private void btnContinueOnclick() {

        TinyDB tinyDB = new TinyDB(MainActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("sign up");

        LayoutInflater inflater = this.getLayoutInflater();
        View registerLayout = inflater.inflate(R.layout.input_phone_layout , null);

        MaterialEditText phoneNumber_mET = registerLayout.findViewById(R.id.phonenumber_signup_edit);
        Button ok_btn = registerLayout.findViewById(R.id.ok_signup_btn);

        builder.setView(registerLayout);

        final AlertDialog dialog = builder.create();

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if (TextUtils.isEmpty(phoneNumber_mET.getText().toString())){
                   Toast.makeText(MainActivity.this, "please enter phone number", Toast.LENGTH_SHORT).show();
                   return;
               }

                dialog.dismiss();

               tinyDB.putString(Consts.PHONE_NUMBER_REGISTERATION , phoneNumber_mET.getText().toString());

                Toast.makeText(MainActivity.this, "registration is successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });



        dialog.show();


    }



}
