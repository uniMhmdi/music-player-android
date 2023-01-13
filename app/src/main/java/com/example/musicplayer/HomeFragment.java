package com.example.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.MyResponse;
import com.example.musicplayer.models.Song;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    private RetrofitInterface retrofitInterface;
    private RecyclerView latestRv;
    private RecyclerView trendingRv;
    private RecyclerView topDayRv;
    private RecyclerView topWeekRv;
    private SongAdapter latestAdapter;
    private SongAdapter trendingAdapter;
    private SongAdapter topDayAdapter;
    private SongAdapter topWeekAdapter;

    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        getMusics();
    }

    private void getMusics() {
        retrofitInterface.getLatestSongs().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.body() != null) {
                    int i = 0;
                    for (Song song : response.body().getSongs()) {
                        latestAdapter.addSong(song);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();
            }
        });

//        retrofitInterface.getTrendingArtist().enqueue(new Callback<MyResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
//                if (response.body() != null) {
//                    for (Song song : response.body().getSongs()) {
//                        trendingAdapter.addSong(song);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
//                Toast.makeText(HomeActivity.this, "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();
//            }
//        });

        retrofitInterface.getTopDaySongs().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.body() != null) {
                    for (Song song : response.body().getSongs()) {
                        topDayAdapter.addSong(song);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();

            }
        });

        retrofitInterface.getTopWeekSongs().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.body() != null) {
                    for (Song song : response.body().getSongs()) {
                        topWeekAdapter.addSong(song);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init(View view) {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        latestRv = view.findViewById(R.id.rv_latest);
        trendingRv = view.findViewById(R.id.rv_trending_artist);
        topDayRv = view.findViewById(R.id.rv_top10_day);
        topWeekRv = view.findViewById(R.id.rv_top10_week);
        latestRv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        trendingRv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        topDayRv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        topWeekRv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        latestAdapter = new SongAdapter(new ArrayList<>());
        trendingAdapter = new SongAdapter(new ArrayList<>());
        topDayAdapter = new SongAdapter(new ArrayList<>());
        topWeekAdapter = new SongAdapter(new ArrayList<>());
        latestRv.setAdapter(latestAdapter);
        trendingRv.setAdapter(trendingAdapter);
        topDayRv.setAdapter(topDayAdapter);
        topWeekRv.setAdapter(topWeekAdapter);


    }
}