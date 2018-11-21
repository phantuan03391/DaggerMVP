package com.example.kyo.daggermvp.di.component;

import android.app.Application;

import com.example.kyo.daggermvp.App;
import com.example.kyo.daggermvp.di.module.ActivityBindingModule;
import com.example.kyo.daggermvp.di.module.AppModule;
import com.example.kyo.daggermvp.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        NetworkModule.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
