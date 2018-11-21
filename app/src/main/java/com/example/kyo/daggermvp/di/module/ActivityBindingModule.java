package com.example.kyo.daggermvp.di.module;

import com.example.kyo.daggermvp.di.ActivityScope;
import com.example.kyo.daggermvp.ui.detail.BlogDetailActivity;
import com.example.kyo.daggermvp.ui.detail.BlogDetailModule;
import com.example.kyo.daggermvp.ui.main.MainActivity;
import com.example.kyo.daggermvp.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = BlogDetailModule.class)
    abstract BlogDetailActivity blogDetailActivity();
}
