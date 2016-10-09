package com.movie.liam.movieapp.detail;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.base.InjectedFragment;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.model.Image;
import com.movie.liam.movieapp.model.Results;
import com.movie.liam.movieapp.utils.ConfigurationManager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailFragment extends InjectedFragment<MainComponent> {

    @Bind(R.id.imageView) public ImageView image;
    @Bind(R.id.details) public TextView details;
    @Bind(R.id.title) public TextView title;
    public static final String KEY = "details";
    private Results results;

    @Inject ConfigurationManager configurationManager;

    public static DetailFragment newInstance(Parcelable results) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY, results);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        if (null != getArguments()) {
            results = getArguments().getParcelable(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        String path = configurationManager.getUrl(results.getPosterPath(), Image.Type.POSTER);
        Picasso.with(getContext()).load(path).into(image);
        details.setText(results.getOverview());
        title.setText(results.getTitle());
    }
}
