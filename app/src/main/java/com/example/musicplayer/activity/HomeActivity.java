package com.example.musicplayer.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.musicplayer.fragment.HomeFragment;
import com.example.musicplayer.R;
import com.example.musicplayer.fragment.SearchFragment;
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
    private boolean flag = true;

    private boolean isTrendArtist;
    private static String artistName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();


        onTrendArtistClicked = new OnTrendArtistClicked() {
            @Override
            public void onClick(String artistName) {
                isTrendArtist = true;
                HomeActivity.artistName = artistName;
                bottomNav.setSelectedItemId(R.id.menu_search);
            }
        };


        integerDeque.push(R.id.menu_home);
        loadFragment(HomeFragment.newInstance(onTrendArtistClicked));
        bottomNav.setSelectedItemId(R.id.menu_home);


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (integerDeque.contains(id)) {
                    if (id == R.id.menu_home) {
                        if (integerDeque.size() != 1) {
                            if (flag) {
                                integerDeque.addFirst(R.id.menu_home);
                                flag = false;
                            }
                        }
                    }
                    integerDeque.remove(id);
                }
                integerDeque.push(id);
                loadFragment(getFragment(item.getItemId()));
                return true;
            }
        });
    }

    private Fragment getFragment(int itemId) {
        switch (itemId) {
            case R.id.menu_home:
                bottomNav.getMenu().getItem(0).setChecked(true);
                artistName = "";
                isTrendArtist = false;
                return HomeFragment.newInstance(onTrendArtistClicked);
            case R.id.menu_search:
                bottomNav.getMenu().getItem(1).setChecked(true);
                if (isTrendArtist) {
                    return SearchFragment.newInstance(artistName);
                }
                return SearchFragment.newInstance();
        }
        bottomNav.getMenu().getItem(0).setChecked(true);
        return HomeFragment.newInstance(onTrendArtistClicked);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        integerDeque.pop();
        if (!integerDeque.isEmpty()) {
            loadFragment(getFragment(integerDeque.peek()));
        } else {
            finish();
        }
    }

    private void init() {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        bottomNav = findViewById(R.id.bottom_nav);

    }

    public interface OnTrendArtistClicked {
        void onClick(String artistName);
    }

}