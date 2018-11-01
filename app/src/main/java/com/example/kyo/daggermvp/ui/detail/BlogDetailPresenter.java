package com.example.kyo.daggermvp.ui.detail;

import com.example.kyo.daggermvp.data.api.DemoApiService;
import com.example.kyo.daggermvp.data.api.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BlogDetailPresenter implements BlogDetailContract.Presenter {
    private BlogDetailContract.View view;
    private CompositeDisposable compositeDisposable;
    private DemoApiService apiService;

    public BlogDetailPresenter(BlogDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
        apiService = RetrofitClient.getRetrofit().create(DemoApiService.class);
    }

    @Override
    public void loadBlogDetail(String id) {
        view.showLoadingUi();

        compositeDisposable.clear();

        Disposable disposable = apiService.getBlogDetail(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        article -> {
                            view.hideLoadingUi();
                            view.showBlogDetail(article);
                        }, throwable -> {
                            view.hideLoadingUi();
                            view.showError(throwable);
                        });

        compositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
