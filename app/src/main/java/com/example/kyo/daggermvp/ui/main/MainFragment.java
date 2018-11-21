package com.example.kyo.daggermvp.ui.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kyo.daggermvp.R;
import com.example.kyo.daggermvp.adapter.ArticleAdapter;
import com.example.kyo.daggermvp.data.model.Article;
import com.example.kyo.daggermvp.di.ActivityScope;
import com.example.kyo.daggermvp.impl.OnMainClickedListener;
import com.example.kyo.daggermvp.ui.detail.BlogDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScope
public class MainFragment extends DaggerFragment implements MainContract.View, OnMainClickedListener {
    private static final String TAG = "kyo";

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar loadingProgress;
    private RecyclerView rcvArticle;
    private LinearLayoutManager layoutManager;

    private ArrayList<Article> articles;
    private ArticleAdapter articleAdapter;

    @Inject
    MainContract.Presenter presenter;

    @Inject
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // do something
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        loadingProgress = view.findViewById(R.id.loadingProgress);

        // init recycler view
        rcvArticle = view.findViewById(R.id.rcvArticle);
        articles = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        rcvArticle.setLayoutManager(layoutManager);
        articleAdapter = new ArticleAdapter(getActivity(), articles, this);
        rcvArticle.setAdapter(articleAdapter);

        addEvents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private void addEvents() {
        getBlogArticle();
    }

    private void getBlogArticle() {
        presenter.takeView(this);
    }

    @Override
    public void showBlogArticle(List<Article> articleList) {
        Log.e(TAG, "showBlogArticle: " + articleList);
        articles.addAll(articleList);
        articleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable error) {
        Log.e(TAG, "showError: " + error.getMessage());
        Snackbar.make(swipeRefreshLayout, error.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", v -> getBlogArticle())
                .show();
    }

    @Override
    public void showLoadingUi() {
        loadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingUi() {
        loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(int position) {
        final Article article = articles.get(position);
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("article_id", article.getId());
        startActivity(intent);
    }
}
