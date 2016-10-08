package com.movie.liam.movieapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.adapter.RecyclerViewAdapter;
import com.movie.liam.movieapp.base.InjectedFragment;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.model.Results;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lduf0001 on 06/10/2016.
 */
public class MainFragment extends InjectedFragment<MainComponent> implements MainContract.MainView {
    @Bind(R.id.progress) public ProgressBar progressIndicator;
    @Bind(R.id.search_result) public RecyclerView recyclerView;

    @Inject MainContract.MainPresenter mainPresenter;
    @Inject RecyclerViewAdapter viewAdapter;
    GridLayoutManager gridLayoutManager;

    List<Results> itemList = new ArrayList<>();


    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupViewAdapter();
    }

    private void setupViewAdapter() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addOnScrollListener(infinateScrollListener());
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.onViewAttached(this);
        mainPresenter.fetchDate();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainPresenter.onViewDetached();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void populateList(List<Results> newItems) {
        int index = itemList.size();
        itemList.addAll(newItems);
        viewAdapter.setItems(itemList);
        for(int i=index;i<itemList.size();i++){
            viewAdapter.notifyItemInserted(index);
        }
    }

    @Override
    public void setProgressVisible(int visibility) {
        progressIndicator.setVisibility(visibility);
    }

    private InfiniteScrollListener infinateScrollListener() {
        return new InfiniteScrollListener(1, gridLayoutManager) {
            @Override
            public void onScrolledToEnd(int firstVisibleItemPosition) {
                mainPresenter.fetchMoreData();
            }
        };
    }
}
