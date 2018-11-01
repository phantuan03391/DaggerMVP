package com.example.kyo.daggermvp;

import android.app.Application;

import com.example.kyo.daggermvp.di.component.DaggerNetComponent;
import com.example.kyo.daggermvp.di.component.NetComponent;
import com.example.kyo.daggermvp.di.module.AppModule;
import com.example.kyo.daggermvp.di.module.NetworkModule;

public class App extends Application {
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
