package com.example.kyo.daggermvp.ui.base;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();
}
