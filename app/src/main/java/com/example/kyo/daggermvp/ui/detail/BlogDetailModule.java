package com.example.kyo.daggermvp.ui.detail;

import com.example.kyo.daggermvp.di.ActivityScope;
import com.example.kyo.daggermvp.di.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.example.kyo.daggermvp.ui.detail.BlogDetailActivity.EXTRA_ARTICLE_ID;

@Module
public abstract class BlogDetailModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract BlogDetailFragment blogDetailFragment();

    @ActivityScope
    @Binds
    abstract BlogDetailContract.Presenter blogDetailPresenter(BlogDetailPresenter presenter);

    @Provides
    @ActivityScope
    static String provideBlogDetailId(BlogDetailActivity activity){
        return activity.getIntent().getStringExtra(EXTRA_ARTICLE_ID);
    }
}
