package com.example.kyo.daggermvp.di.module;

import com.example.kyo.daggermvp.di.CustomScope;
import com.example.kyo.daggermvp.ui.detail.BlogDetailContract;
import com.example.kyo.daggermvp.ui.main.MainContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @Provides
    @CustomScope
    MainContract.Presenter provideMainContractPresenter(MainContract.Presenter presenter) {
        return presenter;
    }

    @Provides
    @CustomScope
    BlogDetailContract.Presenter provideBlogContractPresenter(BlogDetailContract.Presenter presenter) {
        return presenter;
    }
}
