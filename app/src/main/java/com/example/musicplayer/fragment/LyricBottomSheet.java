package com.example.musicplayer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicplayer.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class LyricBottomSheet extends BottomSheetDialogFragment {
    private String lyrics;
    private TextView lyricsTv;


    public LyricBottomSheet(String lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lyrics_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lyricsTv = view.findViewById(R.id.tv_text_lyrics);

        lyricsTv.setText(lyrics);
    }

}
