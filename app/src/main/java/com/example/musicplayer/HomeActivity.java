package com.example.musicplayer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayDeque;
import java.util.Deque;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private RetrofitInterface retrofitInterface;

    private OnTrendArtistClicked onTrendArtistClicked;
    private BottomNavigationView bottomNav;

    private Deque<Integer> integerDeque = new ArrayDeque<>(2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        onTrendArtistClicked = new OnTrendArtistClicked() {
            @Override
            public void onClick(String artistName) {
                bottomNav.getMenu().getItem(1).setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, SearchFragment.newInstance(artistName)).addToBackStack(null).commit();
            }
        };

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, HomeFragment.newInstance(onTrendArtistClicked)).commit();
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, HomeFragment.newInstance(onTrendArtistClicked)).addToBackStack(null).commit();
                        break;
                    case R.id.menu_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, SearchFragment.newInstance()).addToBackStack(null).commit();
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

    public interface OnTrendArtistClicked {
        void onClick(String artistName);
    }

}