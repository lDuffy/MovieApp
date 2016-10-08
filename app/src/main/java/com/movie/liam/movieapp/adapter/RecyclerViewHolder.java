package com.movie.liam.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.movie.liam.movieapp.R;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView photo;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        photo = (ImageView) itemView.findViewById(R.id.photo);
    }

}