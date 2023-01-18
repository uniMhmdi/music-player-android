package com.example.musicplayer;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.SearchResponse;
import com.example.musicplayer.models.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    private RetrofitInterface retrofitInterface;
    private RecyclerView searchRv;
    private SongAdapter searchAdapter;
    private EditText searchEt;
    public static String ARTIST_NAME_KEY;
    private String artistName;
    private boolean isFromHomePage;

    public SearchFragment() {

    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    public static SearchFragment newInstance(String artistName) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARTIST_NAME_KEY, artistName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().getString(ARTIST_NAME_KEY) != null) {
            isFromHomePage = true;
            artistName = getArguments().getString(ARTIST_NAME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (isFromHomePage) {
            searchEt.setText(artistName);
        }

    }

    private void getSearch(String keyword) {
        retrofitInterface.search(keyword).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                searchAdapter.clear();
                if (response.body() != null) {
                    List<SearchResult> searchResultList = new ArrayList<>();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        searchResultList = response.body().getSearchResultList().stream().filter(x -> x.getType().equals("song")).collect(Collectors.toList());
                    }
                    for (SearchResult item : searchResultList) {
                        searchAdapter.addSong(item.getSong());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "سرچ ناموفق بود - خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init(View view) {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        searchRv = view.findViewById(R.id.rv_search_result);
        searchEt = view.findViewById(R.id.et_search);
        searchRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        searchAdapter = new SongAdapter(new ArrayList<>(), true);
        searchRv.setAdapter(searchAdapter);
    }
}