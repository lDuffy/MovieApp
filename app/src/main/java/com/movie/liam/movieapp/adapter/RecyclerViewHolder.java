package com.movie.liam.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.movie.liam.movieapp.R;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView photo;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        photo = (ImageView)itemView.findViewById(R.id.photo);
    }

    @Override
    public void onClick(View view) {
    }
}