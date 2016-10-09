package com.movie.liam.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.liam.movieapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.photo) public ImageView photo;
    @Bind(R.id.year) public TextView year;
    @Bind(R.id.genre) public TextView genre;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}