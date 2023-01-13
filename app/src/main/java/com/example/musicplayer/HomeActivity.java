package com.example.musicplayer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private RetrofitInterface retrofitInterface;


    private BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, HomeFragment.newInstance()).commit();
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, HomeFragment.newInstance()).commit();
                        break;
                    case R.id.menu_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, SearchFragment.newInstance()).commit();
                        break;
                }
                return true;
            }
        });
    }

    private void init() {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        bottomNav = findViewById(R.id.bottom_nav);

    }

}