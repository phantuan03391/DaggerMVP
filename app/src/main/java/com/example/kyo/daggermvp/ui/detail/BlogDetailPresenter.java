package com.example.kyo.daggermvp.ui.detail;

import com.example.kyo.daggermvp.data.api.DemoApiService;
import com.example.kyo.daggermvp.data.model.Article;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class BlogDetailPresenter implements BlogDetailContract.Presenter {
    private BlogDetailContract.View view;
    private CompositeDisposable compositeDisposable;
    private Retrofit retrofit;

//    public BlogDetailPresenter(BlogDetailContract.View view) {
//        this.view = view;
//        view.setPresenter(this);
//        compositeDisposable = new CompositeDisposable();
//    }

    @Inject
    public BlogDetailPresenter(Retrofit retrofit) {
        this.retrofit = retrofit;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadBlogDetail(String id) {
        view.showLoadingUi();

        compositeDisposable.clear();

        Disposable disposable = getBlogDetail(id)
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

    private Observable<Article> getBlogDetail(String id) {
        return retrofit.create(DemoApiService.class).getBlogDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void takeView(BlogDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        compositeDisposable.clear();
        this.view = null;
    }
}
