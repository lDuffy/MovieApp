package com.movie.liam.movieapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.adapter.RecycleViewClickListener;
import com.movie.liam.movieapp.adapter.RecyclerViewAdapter;
import com.movie.liam.movieapp.base.InjectedFragment;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.model.Results;
import com.movie.liam.movieapp.utils.Launcher;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by lduf0001 on 06/10/2016.
 * Fragment for displkaying popular movie list.
 */
public class MainFragment extends InjectedFragment<MainComponent> implements MainContract.MainView {

    public static final int GRID_SIZE = 3;
    @Bind(R.id.progress) public ProgressBar progressIndicator;
    @Bind(R.id.search_result) public RecyclerView recyclerView;
    @Bind(R.id.swipeContainer) public SwipeRefreshLayout swipe;

    @Inject MainContract.MainPresenter mainPresenter;
    @Inject RecyclerViewAdapter viewAdapter;
    @Inject Launcher launcher;
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
        setHasOptionsMenu(true);
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
        swipe.setOnRefreshListener(() -> mainPresenter.fetchDate());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.refresh == item.getItemId()) {
            mainPresenter.sortAndInvalidate(itemList);
            return true;
        }
        return false;
    }

    private void setupViewAdapter() {
        gridLayoutManager = new GridLayoutManager(getActivity(), GRID_SIZE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addOnScrollListener(infinateScrollListener());
        recyclerView.addOnItemTouchListener(
                new RecycleViewClickListener(getContext(), (view, position) -> launcher.openDetail(itemList.get(position)))
        );
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
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void populateList(List<Results> newItems) {
        int index = itemList.size();
        itemList.addAll(newItems);
        itemList = mainPresenter.removeDuplicates(itemList);
        viewAdapter.setItems(itemList);
        for (int i = index; i < itemList.size(); i++) {
            viewAdapter.notifyItemInserted(index);
        }
    }

    private void stopRefreshing() {
        getActivity().runOnUiThread(() -> swipe.setRefreshing(false));
    }

    @Override
    public void setProgressVisible(int visibility) {
        if (GONE == visibility) {
            stopRefreshing();
        }
        progressIndicator.setVisibility(visibility);
    }

    @Override
    public void invalidateAdapter() {
        viewAdapter.notifyDataSetChanged();
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
