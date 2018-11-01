package com.example.kyo.daggermvp.ui.main;

import com.example.kyo.daggermvp.data.model.Article;
import com.example.kyo.daggermvp.ui.base.BasePresenter;
import com.example.kyo.daggermvp.ui.base.BaseView;

import java.util.List;

public interface MainContract {
    interface Presenter extends BasePresenter {
        void loadBlogArticle(int start);
    }

    interface View extends BaseView<Presenter> {
        void showBlogArticle(List<Article> articleList);

        void showError(Throwable error);
    }
}
