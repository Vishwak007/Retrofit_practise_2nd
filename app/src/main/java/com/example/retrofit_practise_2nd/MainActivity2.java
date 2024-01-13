package com.example.retrofit_practise_2nd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
    private BottomNavigationView nav ;
    private ImageView moon ;
    RelativeLayout cont;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nav = findViewById(R.id.btm_nav);
        cont = findViewById(R.id.cont);
        moon = findViewById(R.id.moon);
        cont.setBackground(nav.getBackground());

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                double width = moon.getRootView().getWidth();
                int width_single_item = 0;
                if (item.getItemId() == R.id.search){

                }else if (item.getItemId() == R.id.option_1){
                    width_single_item = (int)(width/4)*1;

                }else if (item.getItemId() == R.id.option_2){
                    width_single_item = (int)(width/4)*2;

                }else{
                    width_single_item = (int)(width/4)*3;

                }
                RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) moon.getLayoutParams();
                layoutParams.setMargins(12+width_single_item, 0, 0, 0);
                moon.setLayoutParams(layoutParams);
                return true;
            }
        });
    }
}