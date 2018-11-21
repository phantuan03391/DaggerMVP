package com.example.kyo.daggermvp.ui.detail;

import android.os.Bundle;

import com.example.kyo.daggermvp.R;
import com.example.kyo.daggermvp.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class BlogDetailActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_ARTICLE_ID = "article_id";

    @Inject
    BlogDetailPresenter presenter;

    @Inject
    Lazy<BlogDetailFragment> blogDetailFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        BlogDetailFragment blogDetailFragment = (BlogDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (blogDetailFragment == null) {
            blogDetailFragment = blogDetailFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), blogDetailFragment, R.id.contentFrame);
        }
    }
}
