package com.example.kyo.daggermvp.ui.main;

import com.example.kyo.daggermvp.di.ActivityScope;
import com.example.kyo.daggermvp.di.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ActivityScope
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
}
