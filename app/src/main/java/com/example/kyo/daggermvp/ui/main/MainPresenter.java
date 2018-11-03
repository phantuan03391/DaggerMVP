package com.example.kyo.daggermvp.ui.main;

import com.example.kyo.daggermvp.data.api.DemoApiService;
import com.example.kyo.daggermvp.data.model.Article;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mainView;
    private Retrofit retrofit;
    private CompositeDisposable compositeDisposable;
//    private DemoApiService apiService;

    @Inject
    public MainPresenter(Retrofit retrofit) {
        this.retrofit = retrofit;
        compositeDisposable = new CompositeDisposable();
//        apiService = RetrofitClient.getRetrofit().create(DemoApiService.class);
//        mainView.setPresenter(this);
    }

    @Override
    public void loadBlogArticle(int start) {
        mainView.showLoadingUi();

        compositeDisposable.clear();

        Disposable disposable = getBlog(start).subscribe(
                articles -> {
                    mainView.hideLoadingUi();
                    mainView.showBlogArticle(articles);
                }, throwable -> {
                    mainView.hideLoadingUi();
                    mainView.showError(throwable);
                });

        compositeDisposable.add(disposable);
    }

    private Observable<List<Article>> getBlog(int start) {
        return retrofit.create(DemoApiService.class).getBlog(start).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void takeView(MainContract.View view) {
        this.mainView = view;
        loadBlogArticle(0);
    }

    @Override
    public void dropView() {
        compositeDisposable.clear();
        mainView = null;
    }
}
