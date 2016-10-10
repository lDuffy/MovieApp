package com.movie.liam.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.model.Image;
import com.movie.liam.movieapp.model.Results;
import com.movie.liam.movieapp.utils.ConfigurationManager;
import com.squareup.picasso.Picasso;

/**
 * Created by lduf0001 on 08/10/2016.
 * Adapter for movie list items. found in MainFragment
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Results> itemList;
    private Context context;
    private ConfigurationManager configurationManager;

    @Inject
    public RecyclerViewAdapter(Context context, ConfigurationManager configurationManager) {
        this.context = context;
        this.configurationManager = configurationManager;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new RecyclerViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (null == itemList) {
            return;
        }
        Results result = itemList.get(position);

        String path = configurationManager.getUrl(result.getPosterPath(), Image.Type.POSTER);
        Picasso.with(context).load(path).into(holder.photo);
        holder.year.setText(result.getReleaseDate());
        String genre = ConfigurationManager.genres.getGenreById(result.getGenreIds()[0]);
        holder.genre.setText(genre);
    }

    public void setItems(List<Results> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getItemCount() {
        if (null == itemList) {
            return 0;
        }
        return itemList.size();
    }
}