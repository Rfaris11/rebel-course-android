package com.rebelworks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rebelworks.course.R;
import com.rebelworks.model.response.MovieResponse;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter {

    private ArrayList<MovieResponse> arrayMovie;

    public HomeAdapter(ArrayList<MovieResponse> arrayMovie) {
        this.arrayMovie = arrayMovie;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_movie_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
