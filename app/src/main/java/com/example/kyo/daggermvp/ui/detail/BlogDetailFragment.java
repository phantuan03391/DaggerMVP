package com.example.kyo.daggermvp.ui.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kyo.daggermvp.R;
import com.example.kyo.daggermvp.data.model.Article;
import com.example.kyo.daggermvp.di.ActivityScope;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@ActivityScope
public class BlogDetailFragment extends DaggerFragment implements BlogDetailContract.View {
    private static final String TAG = "kyo";
    private ProgressBar progressBar;

    @Inject
    BlogDetailContract.Presenter presenter;

    @Inject
    String articleId;

    @Inject
    public BlogDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.loadingProgress);
        Log.e(TAG, "onViewCreated: " + articleId);
        presenter.takeView(this);
        presenter.loadBlogDetail(articleId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void showBlogDetail(Article article) {
        Log.e(TAG, "showBlogDetail: " + article.toString());
    }

    @Override
    public void showError(Throwable throwable) {
        Log.e(TAG, "showError: " + throwable.getMessage());
    }

    @Override
    public void showLoadingUi() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingUi() {
        progressBar.setVisibility(View.GONE);
    }
}
