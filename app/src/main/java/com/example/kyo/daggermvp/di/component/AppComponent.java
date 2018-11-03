package com.example.kyo.daggermvp.di.component;

import com.example.kyo.daggermvp.di.CustomScope;
import com.example.kyo.daggermvp.di.module.ActivityModule;
import com.example.kyo.daggermvp.ui.detail.BlogDetailActivity;
import com.example.kyo.daggermvp.ui.main.MainActivityModule;
import com.example.kyo.daggermvp.ui.main.MainActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = NetComponent.class, modules = ActivityModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(BlogDetailActivity blogDetailActivity);
}
