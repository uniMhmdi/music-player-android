package com.example.musicplayer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class LyricBottomSheet extends BottomSheetDialogFragment {
    private String lyrics;

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
//        rv = view.findViewById(R.id.rv_search);
//        notFoundTv = view.findViewById(R.id.tv_not_found);
//        notFoundIv = view.findViewById(R.id.iv_not_found);
//
//        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
//        list = new ArrayList<>();
//        taskAdapter = new TaskAdapter(list);
//        rv.setAdapter(taskAdapter);
//
//        if (response.size() == 0) {
//            notFoundTv.setVisibility(View.VISIBLE);
//            notFoundIv.setVisibility(View.VISIBLE);
//            rv.setVisibility(View.GONE);
//
//        } else {
//            notFoundTv.setVisibility(View.GONE);
//            notFoundIv.setVisibility(View.GONE);
//            rv.setVisibility(View.VISIBLE);
//            for (TaskCategory item : response) {
//                taskAdapter.addTask(item);
//            }
//        }
    }

//    public void updateList(List<TaskCategory> newList) {
//        if (newList.size() == 0) {
//            notFoundTv.setVisibility(View.VISIBLE);
//            notFoundIv.setVisibility(View.VISIBLE);
//            rv.setVisibility(View.GONE);
//        } else {
//            notFoundTv.setVisibility(View.GONE);
//            notFoundIv.setVisibility(View.GONE);
//            rv.setVisibility(View.VISIBLE);
//            taskAdapter.addNewList(newList);
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
//        onBottomSheetResume.onResume();
    }
}
