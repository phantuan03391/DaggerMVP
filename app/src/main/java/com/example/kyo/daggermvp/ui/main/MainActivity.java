package com.example.kyo.daggermvp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.kyo.daggermvp.App;
import com.example.kyo.daggermvp.R;
import com.example.kyo.daggermvp.adapter.ArticleAdapter;
import com.example.kyo.daggermvp.data.model.Article;
import com.example.kyo.daggermvp.di.component.DaggerAppComponent;
import com.example.kyo.daggermvp.impl.OnMainClickedListener;
import com.example.kyo.daggermvp.ui.detail.BlogDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View, OnMainClickedListener {
    private static final String TAG = "kyo";

    @Inject
    MainPresenter mainPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar loadingProgress;
    private RecyclerView rcvArticle;
    private LinearLayoutManager layoutManager;

    private ArrayList<Article> articles;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        DaggerAppComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .build().inject(this);

        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        loadingProgress = findViewById(R.id.loadingProgress);

        // init recycler view
        rcvArticle = findViewById(R.id.rcvArticle);
        articles = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rcvArticle.setLayoutManager(layoutManager);
        articleAdapter = new ArticleAdapter(this, articles, this);
        rcvArticle.setAdapter(articleAdapter);
    }

    private void addEvents() {
        getBlogArticle();
    }

    private void getBlogArticle() {
        mainPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dropView();
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
        Intent intent = new Intent(this, BlogDetailActivity.class);
        intent.putExtra("article_id", article.getId());
        startActivity(intent);
    }
}
