package com.example.kyo.daggermvp.di.module;

import com.example.kyo.daggermvp.di.CustomScope;
import com.example.kyo.daggermvp.ui.main.MainContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final MainContract.View mainView;

    public MainActivityModule(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Provides
    @CustomScope
    MainContract.View provideMainContractView() {
        return mainView;
    }
}
