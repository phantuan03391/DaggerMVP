package com.example.kyo.daggermvp.di.component;

import com.example.kyo.daggermvp.di.CustomScope;
import com.example.kyo.daggermvp.di.module.MainActivityModule;
import com.example.kyo.daggermvp.ui.main.MainActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
