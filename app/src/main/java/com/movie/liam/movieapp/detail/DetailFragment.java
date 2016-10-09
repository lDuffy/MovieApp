package com.movie.liam.movieapp.detail;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.model.Results;

public class DetailFragment extends Fragment {

    public static final String KEY = "details";
    private Results results;

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
        if (null != getArguments()) {
            results = getArguments().getParcelable(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}
